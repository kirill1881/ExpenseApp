package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.expenseapp.helpers.LoginThread;
import com.example.expenseapp.helpers.RegistrationBody;

public class LoginActivity extends AppCompatActivity {

    private EditText login, password;
    private Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        enter = findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationBody registrationBody = new RegistrationBody(login.getText().toString(),
                        password.getText().toString());
                LoginThread thread = new LoginThread(registrationBody);
                thread.start();
                while (thread.isAlive());
                Log.e("answer", thread.getAuth());
                SharedPreferences sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("login", registrationBody.login);
                editor.apply();
            }
        });
    }
}