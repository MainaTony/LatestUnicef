package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
//import cn.pedant.SweetAlert.SweetAlertDialog;SweetAlertDialog

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mediawatch.ApiResponse.DataApiResponse;
import com.example.mediawatch.ApiResponse.Project;
import com.example.mediawatch.ApiResponse.ProjectsAdapter;
import com.example.mediawatch.databinding.ActivityMainBinding;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collections;
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
        RecyclerView newsFeed = findViewById(R.id.recycler_view_news_feed);

//        Project project = new Project("news Headline", "news Date");
        Project[] projects = {
                new Project("KCPE Exams", "11-28-2023"),
                new Project("Ruto in Russia", "11-27-2023"),
                new Project("Mcsk Battles", "11-26-2023"),
                new Project("Rails on Billateral Talks", "11-25-2023")
        };
        ProjectsAdapter adapter = new ProjectsAdapter(projects);
        newsFeed.setAdapter(adapter);


//        String[] strings = {"one","two","three"};
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);

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
//        JsonObject jsonObject = new JsonObject();

        JSONArray jsonArray = new JSONArray();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle successful array response
//                        Log.d(TAG, "onBettyResponse"+response);

//                        try {
//                            JSONArray jsonArray1 = new JSONArray(response);
//                            Log.d(TAG, "dataItem"+jsonArray1);
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
                        Gson gson = new Gson();
//                        DataApiResponse myResponse = gson.fromJson(String.valueOf(response), DataApiResponse.class);
                        Type type = new TypeToken<List<DataApiResponse>>(){}.getType();
                        List<DataApiResponse> dataApiResponseList = gson.fromJson(String.valueOf(response), type);
                        // Convert List to array if needed
                        DataApiResponse[] dataApiResponseArray = dataApiResponseList.toArray(new DataApiResponse[0]);

                        Log.d(TAG, "mkuru2: " + dataApiResponseArray[1]);
//                            RecyclerView newsFeed = findViewById(R.id.recycler_view_news_feed);
//                            HomeAdapter homeAdapter = new HomeAdapter(dataApiResponseArray);
//                            newsFeed.setAdapter(homeAdapter);

                        try {

//                            List<DataApiResponse> dataApiResponse2 = Collections.singletonList(gson.fromJson(String.valueOf(response), DataApiResponse.class));
//                            DataApiResponse[] dataApiResponse = new DataApiResponse[]{gson.fromJson(String.valueOf(response), DataApiResponse.class)};


//                            RecyclerView newsFeed = findViewById(R.id.recycler_view_news_feed);
//                            HomeAdapter homeAdapter = new HomeAdapter(dataApiResponse);
//                            newsFeed.setAdapter(homeAdapter);

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
//                                Gson gson = new Gson();
//                                DataApiResponse dataApiResponse = gson.fromJson(String.valueOf(jsonObject), DataApiResponse.class);
//                                Log.d(TAG, "duale "+dataApiResponse.getTime());

//                                String dataItem = jsonObject.getString("id");
//                                String time= jsonObject.getString("time");



                                // Call a method or perform actions based on the specific array item
//                                handleArrayItem(dataItem);
//                                Toast.makeText(MainActivity.this, "My Number is: "+dataItem, Toast.LENGTH_SHORT).show();
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
                        Log.e(TAG, "onTonyResponse: " + error.toString());

                        // Call a method or perform actions based on the specific error
                        handleError(error);
                    }
                });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

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