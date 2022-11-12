package com.example.expenseapp.helpers;



import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

public class LoginThread extends Thread{
    private String login;
    private String password;

    public LoginThread(String login, String password){
        this.login = login;
        this.password = password;
    }
    @Override
    public void run(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpUriRequest httpUriRequest = RequestBuilder.post()
                    .setUri(new URI(Constants.url+"/user/login"))
                    .addParameter("login", login)
                    .addParameter("password", password)
                    .build();
            CloseableHttpResponse httpResponse =
                    httpClient.execute(httpUriRequest);
            HttpEntity entity = httpResponse.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Log.e("str", responseString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
