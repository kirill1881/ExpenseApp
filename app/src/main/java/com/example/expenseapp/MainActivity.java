package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(MainActivity.this);

        if (sharedPreferences.getAll().get("login")==null){
            Intent intent = new Intent(MainActivity.this, ChoseActivity.class);
            startActivity(intent);
        }

    }
}