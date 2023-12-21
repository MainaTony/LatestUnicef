package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatCreateChannel extends AppCompatActivity {
    ImageView chat_create_channels_back_button;
    EditText chat_create_channels_edit_text;
    Button chat_create_channels_save_channel;
    private DatabaseReference groupsRef;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_create_channel);
        chat_create_channels_back_button = findViewById(R.id.chat_create_channels_back_button);
        chat_create_channels_edit_text = findViewById(R.id.chat_create_channels_edit_text);
        chat_create_channels_save_channel = findViewById(R.id.chat_create_channels_save_channel);

        groupsRef = FirebaseDatabase.getInstance().getReference("unicef");
        usersRef = FirebaseDatabase.getInstance().getReference("Users");

//        List<String> members = new ArrayList<>();


//        members.add(getCurrentUserId());  // Add the current user as a member
//        members.add("Joram");
//        members.add("Felicity");
//        members.add("Fowzia");
//        members.add("Kennedy");

        // Create a new group with the current user as the admin


        chat_create_channels_save_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = chat_create_channels_edit_text.getText().toString();
                if(!groupName.equals("")){
//                    createGroup(groupName, members);
                    Toast.makeText(ChatCreateChannel.this, "Group : " +groupName+ "Created Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChatCreateChannel.this, ChatAllChannels.class);
                    startActivity(intent);
                }
            }
        });
        chat_create_channels_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

//    public void createNewTable(String tableName) {
//        // Get a reference to the new node (table)
//        DatabaseReference newTableReference = databaseReference.child(tableName);
//
//        // Add sample data to the new table (optional)
//        newTableReference.child("Group").setValue(tableName);
//    }

//    public void createGroup(String groupName, List<String> members) {
//        // Get the current logged-in user ID
//        String adminId = getCurrentUserId();
//
//        // Get a reference to the new group node
//        DatabaseReference newGroupRef = groupsRef.push();
//
//        // Create the group object
//        Group group = new Group(newGroupRef.getKey(), groupName, adminId, members);
//
//        // Set the group data
//        newGroupRef.setValue(group);
//    }
    private String getCurrentUserId() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUid();
        }
        return null;
    }

//    public void addUserToGroup(String groupId, String userId) {
//        groupsRef.child(groupId).child("members").push().setValue(userId);
//    }

//    public void removeUserFromGroup(String groupId, String userId) {
//        groupsRef.child(groupId).child("members").orderByValue().equalTo(userId)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            snapshot.getRef().removeValue();
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        // Handle error
//                    }
//                });
//    }


}