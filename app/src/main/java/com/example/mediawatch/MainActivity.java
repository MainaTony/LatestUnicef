package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.mediawatch.ApiResponse.MediaWatch;
import com.example.mediawatch.ApiResponse.Project;
import com.example.mediawatch.ApiResponse.ProjectsAdapter;
import com.example.mediawatch.databinding.ActivityMainBinding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProjectsAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private static final String KEY_JSON_ARRAY = "key_json_array";
    private RequestQueue requestQueue;
    ActivityMainBinding binding;
    RecyclerView newsFeed;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_ITEM = "key_item";

    MediaWatch mediaWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        newsFeed = findViewById(R.id.recycler_view_news_feed);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        try{
            makeJsonArrayRequest();
            // To retrieve the stored MediaWatch array
            MediaWatch[] storedMediaWatchArray = getMediaWatchArray(MainActivity.this);
            ProjectsAdapter adapter = new ProjectsAdapter(storedMediaWatchArray, this);
            newsFeed.setAdapter(adapter);
        } catch (Exception e){
            System.out.print("MyException:"+e.toString());
        }




//        String[] strings = {"one","two","three"};
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    // Handle item 1 click
                    break;
                case R.id.news:
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, Discover.class);
//                    intent.putExtra("title", mediaWatch.getTitle());
//                    intent.putExtra("category", mediaWatch.getCategory());
//                    intent.putExtra("summary", mediaWatch.getSummary());
                    startActivity(intent);

                    break;
                // Add more cases for additional menu items
                case R.id.chat:

//                    Intent chatIntent = new Intent(MainActivity.this, ChatHome.class);
                    Intent chatIntent = new Intent(MainActivity.this, ChatOneOnOne.class);
//                    intent.putExtra("title", mediaWatch.getTitle());
//                    intent.putExtra("category", mediaWatch.getCategory());
//                    intent.putExtra("summary", mediaWatch.getSummary());
                    startActivity(chatIntent);

//                    Intent allChannelsIntent = new Intent(MainActivity.this, ChatAllChannels.class);
//                    startActivity(allChannelsIntent);

                    break;
                case R.id.download:
//                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
                    // Handle item 4 click
                    Intent download = new Intent(MainActivity.this, Download.class);
//                    intent.putExtra("title", mediaWatch.getTitle());
//                    intent.putExtra("category", mediaWatch.getCategory());
//                    intent.putExtra("summary", mediaWatch.getSummary());
                    startActivity(download);
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

                        // Convert the JSONArray to a list of MediaWatch
                        List<MediaWatch> mediaWatchList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonMediaWatch = response.getJSONObject(i);
                                mediaWatch = new MediaWatch();
                                mediaWatch.setTitle(jsonMediaWatch.getString("title"));
                                mediaWatch.setCategory(jsonMediaWatch.getString("category"));
                                mediaWatch.setDate(jsonMediaWatch.getString("date"));
                                mediaWatch.setSummary(jsonMediaWatch.getString("summary"));
                                mediaWatch.setTonality(jsonMediaWatch.getString("tonality"));
                                mediaWatchList.add(mediaWatch);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Convert the list to an array
                        MediaWatch[] mediaWatchArray = mediaWatchList.toArray(new MediaWatch[0]);

                        // Save the array in SharedPreferences
                        saveMediaWatchArray(MainActivity.this, mediaWatchArray);



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
//                        Type type = new TypeToken<List<DataApiResponse>>(){}.getType();
//                        List<DataApiResponse> dataApiResponseList = gson.fromJson(String.valueOf(response), type);
//                        // Convert List to array if needed
//                        DataApiResponse[] dataApiResponseArray = dataApiResponseList.toArray(new DataApiResponse[0]);

//                        Type type = new TypeToken<List<MediaWatch>>() {
//                        }.getType();
//                        List<MediaWatch> dataApiResponseList = gson.fromJson(String.valueOf(response), type);
//                        // Convert List to array if needed
//                        MediaWatch[] dataApiResponseArray = dataApiResponseList.toArray(new MediaWatch[0]);
//                        saveItem(MainActivity.this, dataApiResponseArray);

                        // Save a JSON array as a single string in SharedPreferences
                        // Convert the JSON array to a string
                        String jsonString = response.toString();

                        // Save the string in SharedPreferences
                        saveJsonArrayString(MainActivity.this, jsonString);



//                        for (int i = 0; i < dataApiResponseArray.length; i++){
//
//
//                        }




//                        Project[] projects = {
//                                new Project("KCPE Exams", "11-28-2023"),
//                                new Project("Ruto in Russia", "11-27-2023"),
//                                new Project("Mcsk Battles", "11-26-2023"),
//                                new Project("Rails on Billateral Talks", "11-25-2023")
//                        };
//                        ProjectsAdapter adapter = new ProjectsAdapter(dataApiResponseArray);
//                        newsFeed.setAdapter(adapter);

//                        Log.d(TAG, "mkuru2: " + dataApiResponseArray[1]);
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

    public static void saveItem(Context context, MediaWatch[] mediaWatch) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ITEM, Arrays.toString(mediaWatch));
        editor.apply();
    }
    // Retrieve an item from SharedPreferences
    public static String getItem(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_ITEM, null);
    }

    private static void saveJsonArrayString(Context context, String jsonString) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_JSON_ARRAY, jsonString);
        editor.apply();
    }

    private static String getJsonArrayString(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_JSON_ARRAY, null);
    }

    private static void saveMediaWatchArray(Context context, MediaWatch[] mediaWatchArray) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Convert the array to a JSON string before saving
        JSONArray jsonArray = new JSONArray();
        for (MediaWatch mediaWatch : mediaWatchArray) {
            JSONObject jsonMediaWatch = new JSONObject();
            try {
                jsonMediaWatch.put("title", mediaWatch.getTitle());
                jsonMediaWatch.put("category", mediaWatch.getCategory());
                jsonMediaWatch.put("tonality", mediaWatch.getTonality());
                jsonMediaWatch.put("mediatype", mediaWatch.getMediatype());
                jsonArray.put(jsonMediaWatch);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        editor.putString(KEY_JSON_ARRAY, jsonArray.toString());
        editor.apply();
    }


    public static MediaWatch[] getMediaWatchArray(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String jsonArrayString = preferences.getString(KEY_JSON_ARRAY, null);

        if (jsonArrayString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonArrayString);
                MediaWatch[] mediaWatchArray = new MediaWatch[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonMediaWatch = jsonArray.getJSONObject(i);
                    MediaWatch mediaWatch = new MediaWatch();
                    mediaWatch.setTitle(jsonMediaWatch.getString("title"));
                    mediaWatch.setCategory(jsonMediaWatch.getString("category"));
                    mediaWatch.setDate(jsonMediaWatch.getString("date"));
                    mediaWatch.setSummary(jsonMediaWatch.getString("summary"));
                    mediaWatch.setStoryurl(jsonMediaWatch.getString("storyurl"));
                    mediaWatch.setTonality(jsonMediaWatch.getString("tonality"));
                    mediaWatch.setMediatype(jsonMediaWatch.getString("mediatype"));
                    mediaWatchArray[i] = mediaWatch;
                }
                return mediaWatchArray;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onItemClick(MediaWatch mediaWatch) {
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
//        Toast.makeText(MainActivity.this, "dateToday"+mediaWatch.getDate(), Toast.LENGTH_SHORT).show();
        intent.putExtra("title", mediaWatch.getTitle());
        intent.putExtra("category", mediaWatch.getCategory());
        intent.putExtra("summary", mediaWatch.getSummary());

        startActivity(intent);
//        String dateToday = mediaWatch.getDate();
//        Toast.makeText(this, "dateToday"+position, Toast.LENGTH_SHORT).show();
////        intent.putExtra("Title", );

//        startActivity(intent);
//        String dateToday = mediaWatch.getDate();
//        Toast.makeText(MainActivity.this, "dateToday"+dateToday, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Position is " +position, Toast.LENGTH_SHORT).show();
    }


}