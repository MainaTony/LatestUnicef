package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mediawatch.ApiResponse.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

public class Verify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        Button verify = findViewById(R.id.sendOtpButton);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Verify.this, VerifyOtp.class);
                startActivity(intent);
            }
        });
    }
//    public void makeJsonRequest(String url, final JSONObject params) {
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        // Request a JSON response from the provided URL.
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // Handle the JSON response
//                        System.out.println("Response: " + response.toString());
//                        ApiResponse apiResponse = new ApiResponse();
//
//                        try {
//                            ObjectMapper objectMapper = new ObjectMapper();
//                            apiResponse = objectMapper.readValue(response.toString(), ApiResponse.class);
//                            System.out.println("Parsed Data: " + apiResponse.getResponseCode());
//                            if(apiResponse.getResponseCode().equals("00")){
//                                Toast.makeText(Verify.this, "Member exists in database", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(Verify.this, MainActivity.class);
//                                startActivity(intent);
//                            } else{
//                                Toast.makeText(Verify.this, "Sorry, You don't exists in the database, please sign up", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
////
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle errors
//                        System.out.println("Error: " + error.toString());
//                        Toast.makeText(Verify.this, "Sorry, Member Does exists in database", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        // Add the request to the RequestQueue.
//        queue.add(jsonObjectRequest);
//
//    }
}