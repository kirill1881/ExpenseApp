package com.example.expenseapp;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Alert {
public static Context context;
    public Alert(Object text) {
        if (Looper.myLooper() == null) Looper.prepare();
        Toast.makeText(context, text.toString(), Toast.LENGTH_LONG).show();
    }

}
