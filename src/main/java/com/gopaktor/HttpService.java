package com.gopaktor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author Mikhail Tyamin <a href="mailto:mikhail.tiamine@gmail.com>mikhail.tiamine@gmail.com</a>
 */
@Service
public class HttpService {

    @Autowired
    private OkHttpClient client;

    public void putFile(String url) throws IOException {
        File sourceFile = new File("1.png");

        Request request = new Request.Builder().
                url(url).
                put(RequestBody.create(null, sourceFile)).
                build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
    }
}
