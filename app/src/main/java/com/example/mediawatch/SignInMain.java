package com.example.mediawatch;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SignInMain extends AppCompatActivity {
    String url = "http:// 192.168.137.1:8080/portal/auth/changePassword";
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main);

        Button signUpPageButton = findViewById(R.id.signUpPageButton);
        EditText editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        EditText editTextTextPassword = findViewById(R.id.editTextTextPassword);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        signUpPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!editTextTextEmailAddress2.getText().toString().isEmpty() && !editTextTextPassword.getText().toString().isEmpty()){
                        JSONObject params = new JSONObject();
                        params.put("email", editTextTextEmailAddress2.getText().toString());
                        params.put("password", editTextTextPassword.getText().toString());

//                    makeJsonRequest("\"http://192.168.100.111:8080/portal/auth/authenticate\"", params);
                        makeJsonRequest(url, params);

                    } else{
                        Toast.makeText(SignInMain.this, "Sorry, error occurred", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                                Toast.makeText(SignInMain.this, "Member exists in database", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInMain.this, LoginMain.class);
                                startActivity(intent);
                            } else if(apiResponse.getResponseCode().equals("01")){
                                Toast.makeText(SignInMain.this, "Sorry, Error Occurred", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SignInMain.this, "Sorry, Member Does exists in database", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }

    public void signup(String username, String password){

        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
//                    Add the user to the database
                    reference.child("Users").child(auth.getUid()).child("userName").setValue(username);
                }
            }
        });

    }
}