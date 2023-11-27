package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signUp = findViewById(R.id.signUpButton);
        Button login = findViewById(R.id.loginMainButton);

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
                Intent intent = new Intent(Login.this, LoginMain.class);
                startActivity(intent);
            }
        });
    }
}