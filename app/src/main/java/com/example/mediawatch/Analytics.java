package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Analytics extends AppCompatActivity {
    PieChart pieChart;
    TextView totalThematicAreas, totalMedia, totalTonality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        totalThematicAreas = findViewById(R.id.totalThematicAreas);
        totalMedia = findViewById(R.id.totalMedia);
        totalTonality = findViewById(R.id.totalTonality);
    }

    private void setData()
    {

        // Set the percentage of language used
        totalThematicAreas.setText(Integer.toString(40));
        totalMedia.setText(Integer.toString(30));
        totalTonality.setText(Integer.toString(5));
//        tvJava.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Integer.parseInt(totalThematicAreas.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt(totalMedia.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "C++",
                        Integer.parseInt(totalTonality.getText().toString()),
                        Color.parseColor("#EF5350")));
//        pieChart.addPieSlice(
//                new PieModel(
//                        "Java",
//                        Integer.parseInt(tvJava.getText().toString()),
//                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}