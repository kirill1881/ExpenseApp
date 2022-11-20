package com.example.expenseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expenseapp.helpers.RegisterThread;
import com.example.expenseapp.helpers.RegistrationBody;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name, lastName, login, password, passwordAgain;
    private Button register;
    private TextView notCorrect;
    private ImageView imageView;
    private static final int PICK_IMAGE = 100;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        imageView = findViewById(R.id.image);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastname);
        login  = findViewById(R.id.login);
        password = findViewById(R.id.password);
        passwordAgain = findViewById(R.id.password_again);

        register = findViewById(R.id.register);
        notCorrect = findViewById(R.id.not_correct);

        notCorrect.setVisibility(View.INVISIBLE);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                         PICK_IMAGE);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().
                        equals(passwordAgain.getText().toString())){
                    notCorrect.setVisibility(View.INVISIBLE);
                    RegistrationBody registrationBody = new RegistrationBody(login.getText().toString(),
                            password.getText().toString(), name.getText().toString(),
                            lastName.getText().toString());

                    Log.e("body", registrationBody.toString());
                    RegisterThread registerThread = new RegisterThread(registrationBody);
                    registerThread.start();
                    while (registerThread.isAlive());
                    String str = registerThread.getAuth();
                    if (str.equals("true")){
                        notCorrect.setVisibility(View.VISIBLE);
                        notCorrect.setText("yeah");
                        SharedPreferences sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("login", registrationBody.login);
                        editor.apply();
                    }
                }else {
                    notCorrect.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data!=null){
            Uri selectImageUri = data.getData();
            if (selectImageUri!=null){
                imageView.setImageURI(selectImageUri);
                FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

                Bitmap bitmap = ((BitmapDrawable)(imageView.getDrawable())).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                byte[] bytes = baos.toByteArray();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference storageReference1 = storageReference
                        .child(System.currentTimeMillis()+"");
                UploadTask up = storageReference1.putBytes(bytes);
                Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference1.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        url = task.getResult().toString().substring(71, 84);
                    }
                });
            }
        }
    }
}