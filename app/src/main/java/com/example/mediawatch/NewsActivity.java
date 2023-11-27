package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mediawatch.databinding.ActivityMainBinding;

public class NewsActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_news);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    // Handle item 1 click
                    startActivity(new Intent(NewsActivity.this, MainActivity.class));
                    break;
                case R.id.news:
                    // Handle item 2 click

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
}