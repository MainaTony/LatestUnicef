package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatHome extends AppCompatActivity {
    ImageView chat_room_add_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);
        chat_room_add_img = findViewById(R.id.chat_room_add_img);
        chat_room_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatHome.this, ChatAllChannels.class);
                startActivity(intent);
            }
        });
    }
}