package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mediawatch.ApiResponse.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class VerifyOtp extends AppCompatActivity {
    String url = "http:// 192.168.137.1:8080/portal/auth/verifyOtp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        Button verifyOtp = findViewById(R.id.verifyOtpButton);
        EditText editTextNumberPassword = findViewById(R.id.editTextNumberPassword);

        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyOtp.this, SignInMain.class);
                startActivity(intent);

//                try {
//                    if(!editTextNumberPassword.getText().toString().isEmpty()){
//                        JSONObject params = new JSONObject();
//                        params.put("otp", editTextNumberPassword.getText().toString());
//
////                    makeJsonRequest("\"http://192.168.100.111:8080/portal/auth/authenticate\"", params);
//                        makeJsonRequest(url, params);
//
//                    } else{
//                        Toast.makeText(VerifyOtp.this, "Sorry, error occurred", Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////                Intent intent = new Intent(VerifyOtp.this, MainActivity.class);
////                startActivity(intent);
            }
        });
    }

    public void makeJsonRequest(String url, final JSONObject params) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response
                        System.out.println("Response: " + response.toString());
                        ApiResponse apiResponse = new ApiResponse();

                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            apiResponse = objectMapper.readValue(response.toString(), ApiResponse.class);
                            System.out.println("Parsed Data: " + apiResponse.getResponseCode());
                            if(apiResponse.getResponseCode().equals("00")){
                                Toast.makeText(VerifyOtp.this, "Otp successfully verified, proceeding to change password", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(VerifyOtp.this, SignInMain.class);
                                startActivity(intent);
                            } else if (apiResponse.getResponseCode().equals("01")){
                                Toast.makeText(VerifyOtp.this, "Otp failed, kindly try again", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(VerifyOtp.this, LoginMain.class);
//                                startActivity(intent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

//

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        System.out.println("Error: " + error.toString());
                        Toast.makeText(VerifyOtp.this, "Sorry, Member Does exists in database", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }
}