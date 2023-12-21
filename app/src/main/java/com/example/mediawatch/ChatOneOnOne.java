package com.example.mediawatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import com.aspose.pdf.HtmlLoadOptions;

public class ChatOneOnOne extends AppCompatActivity {
    RecyclerView rv;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseDatabase database;
    String username;
    List<String> list;
    UsersAdapter usersAdapter;
    ProgressBar userListProgressBar;

    ImageView chat_room_add_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_one_on_one);
        rv = findViewById(R.id.rv);
        chat_room_add_img = findViewById(R.id.chat_room_add_img);
        chat_room_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatOneOnOne.this, ChatCreateChannel.class);
                startActivity(intent);
            }
        });

        userListProgressBar = findViewById(R.id.userListProgressBar);
        userListProgressBar.setVisibility(View.VISIBLE);
//        rv.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        reference.child("Users").child(user.getUid()).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username = dataSnapshot.getValue().toString();
                getUsers();
                usersAdapter = new UsersAdapter(list, username, ChatOneOnOne.this);
                rv.setAdapter(usersAdapter);
                userListProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void getUsers(){
        reference.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();

                if(!key.equals(user.getUid())){
                    list.add(key);
                    usersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}