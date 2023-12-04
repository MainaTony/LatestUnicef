package com.example.mediawatch;

//import static com.example.mediawatch.MainActivity.makeJsonArrayRequest;
import static com.example.mediawatch.Utils.JsonApiResponse.makeJsonRequest;
import static com.example.mediawatch.Utils.JsonApiResponse.parseJsonResponse;

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

import java.io.IOException;
import java.util.Map;


public class LoginMain extends AppCompatActivity {
    private EditText email;
    private EditText password;
//    String url = "http://192.168.100.149:8080/portal/auth/authenticate";
    private ProgressBar progressBar;
    String urlFetchData = "https://system.farsightmediawatch.com/index.php/Data_api/fetch_editorial_data";
    private static final String TAG = "YourAppTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loginMainProgressBar);

        Button myLoginButton = findViewById(R.id.myLoginButton);
        myLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    JSONObject params = new JSONObject();
//                        params.put("username", email.getText().toString());
//                        params.put("password", password.getText().toString());
                    makeJsonRequest(urlFetchData, params);
                    Intent intent = new Intent(LoginMain.this, MainActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.INVISIBLE);
                } else{
                    Toast.makeText(LoginMain.this, "Please Enter Your Username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }








}