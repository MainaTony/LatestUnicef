package com.example.mediawatch.Utils;

import com.example.mediawatch.ApiResponse.ApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonApiResponse {

    public static ApiResponse parseJsonResponse(JSONObject response) {
        ApiResponse responseModel = new ApiResponse();
        try {
            // Parse JSON and set values in your model class
            responseModel.setResponseCode(response.getString("responseCode"));
            responseModel.setResponseDescription(response.getString("responseDescription"));
//            responseModel.setField2(response.getInt("field2"));
            // Set more fields as needed
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseModel;
    }


}
