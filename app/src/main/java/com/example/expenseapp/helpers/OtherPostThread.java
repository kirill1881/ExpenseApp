package com.example.expenseapp.helpers;

import androidx.annotation.NonNull;

import org.htmlunit.org.apache.http.HttpResponse;
import org.htmlunit.org.apache.http.client.HttpClient;
import org.htmlunit.org.apache.http.client.methods.HttpUriRequest;
import org.htmlunit.org.apache.http.client.methods.RequestBuilder;
import org.htmlunit.org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class OtherPostThread extends Thread {
    private String resp, status, name, lastname, url;
    private RegistrationBody body;

    public OtherPostThread(String name, String lastname, String url) {
        this.name = name;
        this.lastname = lastname;
        this.url = url;
    }

    public OtherPostThread(RegistrationBody regBody) {
        body = regBody;
    }

    @Override
    public void run() {
        super.run();
        if (name.length() > 0) {
            post("/user/editName", "newName");
        }
        if (lastname.length() > 0) {
            post("/user/editLastName", "newLastName");
        }
        if (url != null) {
            post("/editUrl", "url");
        }
    }

    public String getResp() {
        return resp;
    }
    private void post(String url, String param) {
        try {
            HttpUriRequest httpUriRequest1 =
                    RequestBuilder.post(Constants.url+url)
                            .addParameter("id", String.valueOf(1))
                            .addParameter(param, getParam(param))
                            .setCharset(StandardCharsets.UTF_8)
                            .build();
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpUriRequest1);
            status = String.valueOf(httpResponse.getStatusLine().getStatusCode());
            resp = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent())).readLine();
            System.out.println(resp);
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

    private String getParam(String param) {
        if (param.equals("url")) return url;
        if (param.equals("newName")) return name;
        if (param.equals("newLastName")) return lastname;
        return "param not found";
    }
}
