package com.example.mediawatch.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mediawatch.ApiResponse.ApiResponse;
import com.example.mediawatch.LoginMain;
import com.example.mediawatch.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonApiResponse {

    public static ApiResponse parseJsonResponse(JSONObject response) {
        ApiResponse responseModel = new ApiResponse();
        try {
            // Parse JSON and set values in your model class
            responseModel.setResponseCode(response.getString("responseCode"));
            responseModel.setResponseDescription(response.getString("responseDescription"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseModel;
    }

    public static void makeJsonRequest(String url, final JSONObject params) {
        RequestQueue requestQueue = MyApplication.getInstance().getRequestQueue();

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response
//                        ApiResponse responseModel = parseJsonResponse(response);
                        ApiResponse responseModel = new ApiResponse();
                        try {
                            // Parse JSON and set values in your model class
                            responseModel.setResponseCode(response.getString("responseCode"));
                            responseModel.setResponseDescription(response.getString("responseDescription"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(Constants.TAG, "MyResCode " + responseModel.getResponseCode());
                        Log.d(Constants.TAG, "Description " + responseModel.getResponseDescription());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        System.out.println("Error: " + error.toString());
                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }

}
