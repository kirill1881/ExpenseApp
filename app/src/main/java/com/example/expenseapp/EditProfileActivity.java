package com.example.expenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.expenseapp.helpers.LoginThread;
import com.example.expenseapp.helpers.OtherPostThread;
import com.example.expenseapp.helpers.RegistrationBody;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextInputEditText editName, editLastName;
    private Button save;
    private String sname, slastname, surl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Alert.context = getApplicationContext();
        imageView = findViewById(R.id.image);
        editName = findViewById(R.id.editName);
        editLastName = findViewById(R.id.editLastName);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = editName.getText().toString();
                slastname = editLastName.getText().toString();
                OtherPostThread postTh = new OtherPostThread(sname, slastname, null);
                postTh.start();
            }
        });

    }
}