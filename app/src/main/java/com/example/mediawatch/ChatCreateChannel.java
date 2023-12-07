package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChatCreateChannel extends AppCompatActivity {
    ImageView chat_create_channels_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_create_channel);
        chat_create_channels_back_button = findViewById(R.id.chat_create_channels_back_button);
        chat_create_channels_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}