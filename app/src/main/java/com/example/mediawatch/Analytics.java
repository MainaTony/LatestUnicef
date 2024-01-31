package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONObject;
import android.util.Log;

public class Analytics extends AppCompatActivity {
    PieChart pieChart;
    TextView totalThematicAreas, totalMedia, totalTonality;
    WebView webView;

    ImageView webViewBackImageView;
    private RequestQueue requestQueue;
    private static final String TAG = "Analytics";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        webView = findViewById(R.id.analyticsWebView);
        webViewBackImageView = findViewById(R.id.webViewBackImageView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String url = "https://lookerstudio.google.com/embed/reporting/56ad73d2-fe38-440e-9feb-4b14522f5a33/page/JDvEC";

        webView.loadUrl(url);

        webViewBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Analytics.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Initialize the RequestQueue
//        requestQueue = Volley.newRequestQueue(this);
//        makeGetRequest();

//        totalThematicAreas = findViewById(R.id.thematic);
//        totalMedia = findViewById(R.id.media);
//        totalTonality = findViewById(R.id.tonality);
//        pieChart = findViewById(R.id.piechart);
//        setData();


    }

//    private void setData()
//    {
//
////        PieChart pieChart = new PieChart(Analytics.this); // Replace 'context' with the appropriate context
//        // Set the percentage of language used
//        totalThematicAreas.setText(Integer.toString(40));
//        totalMedia.setText(Integer.toString(30));
//        totalTonality.setText(Integer.toString(5));
////        tvJava.setText(Integer.toString(25));
//
//        // Set the data and color to the pie chart
//        pieChart.addPieSlice(
//                new PieModel(
//                        "Thematic",
//                        Integer.parseInt(totalThematicAreas.getText().toString()),
//                        Color.parseColor("#FFA726")));
////                        Color.parseColor("#0000")));
//        pieChart.addPieSlice(
//                new PieModel(
//                        "Media",
//                        Integer.parseInt(totalMedia.getText().toString()),
//                        Color.parseColor("#66BB6A")));
//        pieChart.addPieSlice(
//                new PieModel(
//                        "Tonality",
//                        Integer.parseInt(totalTonality.getText().toString()),
//                        Color.parseColor("#EF5350")));
////        pieChart.addPieSlice(
////                new PieModel(
////                        "Java",
////                        Integer.parseInt(tvJava.getText().toString()),
////                        Color.parseColor("#29B6F6")));
//
//        // To animate the pie chart
//        pieChart.startAnimation();
//    }

    private void makeGetRequest() {
        String url = "https://lookerstudio.google.com/embed/reporting/56ad73d2-fe38-440e-9feb-4b14522f5a33/page/JDvEC";


        // Create a request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response
                        // response is the JSON object returned from the server
//                        webView = findViewById(R.id.analyticsWebView);
//                        webView.
                        Log.d(TAG, "html: " + response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Log.e(TAG, "myHtmlError: " + error.toString());
                    }
                }
        );

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}