package com.example.expenseapp.helpers;

import android.telecom.Call;

import com.example.expenseapp.helpers.RegistrationBody;

import org.htmlunit.org.apache.http.HttpResponse;
import org.htmlunit.org.apache.http.client.HttpClient;
import org.htmlunit.org.apache.http.client.methods.HttpUriRequest;
import org.htmlunit.org.apache.http.client.methods.RequestBuilder;
import org.htmlunit.org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class RegisterThread extends Thread {
    public int getStatus() {
        return status;
    }

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
          HttpUriRequest httpUriRequest1 =
                   RequestBuilder.post(Constants.url+"/user/register")
                            .addParameter("login", body.login)
                            .addParameter("password", body.password)
                            .addParameter("name", body.name)
                            .addParameter("lastName", body.lastname)
                            .setCharset(StandardCharsets.UTF_8)
                            .build();
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpUriRequest1);
            sleep(1500);
            status = httpResponse.getStatusLine().getStatusCode();
            System.out.println(status);
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
