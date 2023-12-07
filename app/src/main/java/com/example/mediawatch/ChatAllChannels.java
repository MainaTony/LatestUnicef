package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChatAllChannels extends AppCompatActivity {
    Button chat_all_channels_create_channel;
    ImageView chat_all_channels_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_all_channels);
        chat_all_channels_create_channel = findViewById(R.id.chat_all_channels_create_channel);
        chat_all_channels_back_button = findViewById(R.id.chat_all_channels_back_button);
        chat_all_channels_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        chat_all_channels_create_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createChannelActivity = new Intent(ChatAllChannels.this, ChatCreateChannel.class);
                startActivity(createChannelActivity);
            }
        });
    }
}