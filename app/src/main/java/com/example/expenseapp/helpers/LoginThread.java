package com.example.expenseapp.helpers;



import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlunit.org.apache.http.HttpResponse;
import org.htmlunit.org.apache.http.client.HttpClient;
import org.htmlunit.org.apache.http.client.methods.HttpUriRequest;
import org.htmlunit.org.apache.http.client.methods.RequestBuilder;
import org.htmlunit.org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class LoginThread extends Thread{


    public int getStatus() {
        return status;
    }

    public String getAuth() {
        return auth;
    }

    private String auth;
    private RegistrationBody body;
    private int status = 0;
    public LoginThread(RegistrationBody body) {
        this.body = body;
    }


    @Override
    public void run(){
        try {
            Log.e("link", Constants.url+"user/login");
            HttpUriRequest httpUriRequest1 =
                    RequestBuilder.post(Constants.url+"user/login")
                            .addParameter("login", body.login)
                            .addParameter("password", body.password)
                            .setCharset(StandardCharsets.UTF_8)
                            .build();
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpUriRequest1);
            status = httpResponse.getStatusLine().getStatusCode();
            auth = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent())).readLine();
            System.out.println(status);
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
