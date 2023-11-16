package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mediawatch.ApiResponse.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;


public class LoginMain extends AppCompatActivity {
    private EditText email;
    String field1 = "admin";
    String field2 = "admin123";
    private EditText password;
    String url = "http://192.168.100.111:8080/portal/auth/authenticate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

         email = findViewById(R.id.email);
         password = findViewById(R.id.password);

        Button myLoginButton = findViewById(R.id.myLoginButton);
        myLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginMain.this, Verify.class);
//                startActivity(intent);
//                login();
                try {
                    if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                        JSONObject params = new JSONObject();
                        params.put("username", email.getText().toString());
                        params.put("password", password.getText().toString());

//                    makeJsonRequest("\"http://192.168.100.111:8080/portal/auth/authenticate\"", params);
                        makeJsonRequest(url, params);
                    } else{
                        Toast.makeText(LoginMain.this, "Please Enter Your Username or password", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

//    private void login(){
//        String urlMain = "http://your-backend-url/api/login";
//        String url = "http://192.168.100.111:8080/portal/auth/authenticate";
//
//        StringRequest request = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("LoginActivity", "Error: " );
//                        // Handle successful login response
//                        Toast.makeText(LoginMain.this, response, Toast.LENGTH_SHORT).show();
////                        Toast.makeText(LoginMain.this, "Communication To The Backend a success"+"", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle error response
//                        Log.e("LoginActivity", "Error: " + error.getMessage());
//                        Toast.makeText(LoginMain.this, "Login failed", Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                // Set POST parameters for the login request
//                Map<String, String> params = new HashMap<>();
////                params.put("username", email.getText().toString());
//                params.put("username", field1);
////                params.put("password", password.getText().toString());
//                Log.e("Username", "Error: " + field1);
//                params.put("password", field2);
//                Log.e("Password", "Error: " + field2);
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/json");
////                Log.e("Params", +params);
//                return params;
//            }
//        };
//
//        // Add the request to the Volley request queue
//        Volley.newRequestQueue(this).add(request);
//    }

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
                                Toast.makeText(LoginMain.this, "Member exists in database", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginMain.this, MainActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(LoginMain.this, "Sorry, You don't exists in the database, please sign up", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(LoginMain.this, "Sorry, Member Does exists in database", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);


    }




}