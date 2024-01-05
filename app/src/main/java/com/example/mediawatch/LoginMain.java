package com.example.mediawatch;

//import static com.example.mediawatch.MainActivity.makeJsonArrayRequest;
import static com.example.mediawatch.Utils.JsonApiResponse.makeJsonRequest;
import static com.example.mediawatch.Utils.JsonApiResponse.parseJsonResponse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mediawatch.Utils.Constants;
import com.example.mediawatch.Utils.JsonApiResponse;
import com.example.mediawatch.Utils.JsonApiResponse.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mediawatch.ApiResponse.ApiResponse;
import com.example.mediawatch.Utils.MyApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.Map;


public class LoginMain extends AppCompatActivity {
    private EditText email;

    private EditText username;
    private EditText password;
//    String url = "http://192.168.100.149:8080/portal/auth/authenticate";
    private ProgressBar progressBar;
    String urlFetchData = "https://system.farsightmediawatch.com/index.php/Data_api/fetch_editorial_data";
    private static final String TAG = "YourAppTag";

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        FirebaseApp.initializeApp(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loginMainProgressBar);
//        username = findViewById(R.id.username);
        auth = FirebaseAuth.getInstance();

        Button myLoginButton = findViewById(R.id.myLoginButton);
//        myLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                String mail = email.getText().toString();
//                String pass = password.getText().toString();
//                signIn(mail, pass);
//            }
//        });
        myLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginMain.this, "Login successful.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginMain.this, MainActivity.class);
                startActivity(intent);
//                login();
            }
        });


    }

    public void signIn(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginMain.this, MainActivity.class);
                    Toast.makeText(LoginMain.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginMain.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




    public void login() {
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        auth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI or navigate to the next screen
//                            Log.d("LoginActivity", "signInWithEmail:success");
                            Toast.makeText(LoginMain.this, "Login successful.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginMain.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginMain.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }








}