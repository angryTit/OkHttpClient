package com.gopaktor;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;

/**
 * @author Mikhail Tyamin <a href="mailto:mikhail.tiamine@gmail.com>mikhail.tiamine@gmail.com</a>
 */
@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3Client;

    public String generateLinkForUploading(String fileName) {
        Date expiration = new java.util.Date();
        long milliSeconds = expiration.getTime();
        milliSeconds += 1000 * 30;
        expiration.setTime(milliSeconds);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest("mikhail-test-bucket", fileName);
        generatePresignedUrlRequest.setMethod(HttpMethod.PUT);
        generatePresignedUrlRequest.setExpiration(expiration);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
