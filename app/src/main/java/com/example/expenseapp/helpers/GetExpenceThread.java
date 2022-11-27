package com.example.expenseapp.helpers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetExpenceThread extends Thread {
    private List<ExpenseBody> list = new ArrayList<>();
    private String id;

    public List<ExpenseBody> getList() {
        return list;
    }
    public GetExpenceThread(String id){
        this.id = id;
    }


    @Override
    public void run(){
        try {
            Log.e("url",Constants.url+"expense/getById/"+id);
            URL url = new URL(Constants.url+"expense/getById/"+id);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ExpenseBody expenseBody = new ExpenseBody(jsonObject.get("sum").toString(),
                        jsonObject.get("name").toString(),
                        jsonObject.get("time").toString());
                list.add(expenseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
