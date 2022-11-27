package com.example.expenseapp.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telecom.Call;
import android.util.Log;

import com.example.expenseapp.MainActivity;
import com.example.expenseapp.helpers.RegistrationBody;

import org.htmlunit.org.apache.http.HttpResponse;
import org.htmlunit.org.apache.http.client.HttpClient;
import org.htmlunit.org.apache.http.client.methods.HttpUriRequest;
import org.htmlunit.org.apache.http.client.methods.RequestBuilder;
import org.htmlunit.org.apache.http.impl.client.HttpClients;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class RegisterThread extends Thread {
    public int getStatus() {
        return status;
    }

    public String getAuth() {
        return auth;
    }

    private String auth;
    private RegistrationBody body;
    private int status = 0;
    public RegisterThread(RegistrationBody body) {
        this.body = body;
    }
    @Override
    public void run() {
        super.run();
        try {
            //call.execute();
            Log.e("link", Constants.url+"user/register");
          HttpUriRequest httpUriRequest1 =
                   RequestBuilder.post(Constants.url+"user/register")
                            .addParameter("login", body.login)
                            .addParameter("password", body.password)
                            .addParameter("name", body.name)
                            .addParameter("lastName", body.lastname)
                            .addParameter("url", body.url)
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
