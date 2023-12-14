package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

//import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signUp = findViewById(R.id.signUpButton);
        Button login = findViewById(R.id.loginMainButton);
        progressBar = findViewById(R.id.loginProgressBar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new SweetAlertDialog(Login.this)
//                        .setTitleText("Successfully redirecting to sign up")
//                        .show();
                Intent intent = new Intent(Login.this, Verify.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(Login.this, LoginMain.class);
                startActivity(intent);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}