package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name, lastName, login, password, passwordAgain;
    private Button register;
    private TextView notCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastname);
        login  = findViewById(R.id.login);
        password = findViewById(R.id.password);
        passwordAgain = findViewById(R.id.password_again);

        register = findViewById(R.id.register);
        notCorrect = findViewById(R.id.not_correct);

        notCorrect.setVisibility(View.INVISIBLE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().
                        equals(passwordAgain.getText().toString())){
                    notCorrect.setVisibility(View.INVISIBLE);
                }else {
                    notCorrect.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}