package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
//import cn.pedant.SweetAlert.SweetAlertDialog;SweetAlertDialog

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mediawatch.ApiResponse.DataApiResponse;
import com.example.mediawatch.databinding.ActivityMainBinding;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RequestQueue requestQueue;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            requestQueue = Volley.newRequestQueue(this);
            switch (item.getItemId()) {
                case R.id.home:
                    // Handle item 1 click
                    break;
                case R.id.news:
                    // Handle item 2 click
//                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
//                            .setTitleText("Good job!")
//                            .setContentText("You clicked the button!")
//                            .show();
//                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
                    // Make a JSON array request
                    makeJsonArrayRequest();

                    break;
                // Add more cases for additional menu items
                case R.id.chat:
//                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
                    // Handle item 3 click
                    break;
                case R.id.download:
//                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
                    // Handle item 4 click
                    break;
            }
            return false;
        });





    }

//    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

    private void makeJsonArrayRequest() {
        String url = "https://system.farsightmediawatch.com/index.php/Data_api/fetch_editorial_data";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle successful array response
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

//                                DataApiResponse dataApiResponse = response.getJSONObject(i);

                                String dataItem = jsonObject.getString("id");

                                System.out.print("dataItem"+dataItem);
//                                List<DataApiResponse> dataList = new Gson().fromJson(response.toString(), listType);
//                                handleDataList(dataList);
//                                HomeAdapter homeAdapter = new HomeAdapter(dataItem);

                                // Call a method or perform actions based on the specific array item
                                handleArrayItem(dataItem);
                                Toast.makeText(MainActivity.this, "My Number is: "+dataItem, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        Log.e(TAG, "onErrorResponse: " + error.toString());

                        // Call a method or perform actions based on the specific error
                        handleError(error);
                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }

    private void handleArrayItem(String item) {
        // Implement logic for handling each array item
        Log.d(TAG, "handleArrayItem: " + item);

    }

    private void handleError(VolleyError error) {
        // Implement logic for handling the error
        Log.e(TAG, "handleError: " + error.toString());
    }







}