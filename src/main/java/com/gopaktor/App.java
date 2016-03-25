package com.gopaktor;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Mikhail Tyamin <a href="mailto:mikhail.tiamine@gmail.com>mikhail.tiamine@gmail.com</a>
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private HttpService httpService;

    @Bean
    public AmazonS3 s3Client() {
        AmazonS3 client = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
        client.setEndpoint("s3.amazonaws.com");
        return client;
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String s3link = s3Service.generateLinkForUploading("SimpleFileName");
        httpService.putFile(s3link);
    }
}
