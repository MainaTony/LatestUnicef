package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Discover extends AppCompatActivity {
    TextView category_discover_tv;
    RecyclerView category_discover_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

//        category_discover_tv = findViewById(R.id.category_discover_tv);
//        category_discover_recycler = findViewById(R.id.category_discover_recycler);


    }
}