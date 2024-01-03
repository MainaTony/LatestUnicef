package com.example.mediawatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mediawatch.ApiResponse.User;
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

public class ChatAllChannels extends AppCompatActivity {
    Button chat_all_channels_create_channel;
    ImageView chat_all_channels_back_button;

//    UserGroupAdapter userGroupAdapter;
//    private UsersAdapter usersAdapter;

//    UsersAdapter usersAdapter;

    UsersAdapterGroupAddMember usersAdapterGroup;
    private List<User> users;

    private List<String> selectedUsers;

    private String groupId;
    RecyclerView rv2;
//    RecyclerView rv;

    private DatabaseReference groupsRef;
    private DatabaseReference usersRef;

//    Other
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseDatabase database;
    String username;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_all_channels);
        chat_all_channels_create_channel = findViewById(R.id.chat_all_channels_create_channel);
        chat_all_channels_back_button = findViewById(R.id.chat_all_channels_back_button);
        rv2 = findViewById(R.id.rv2);
//        rv = findViewById(R.id.rv);

//        list = new ArrayList<>();
//
//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference();

//        groupsRef = FirebaseDatabase.getInstance().getReference("unicef");
//        usersRef = FirebaseDatabase.getInstance().getReference("Users");

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        rv.setLayoutManager(layoutManager);


//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rv.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        reference.child("Users").child(user.getUid()).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username = dataSnapshot.getValue().toString();
                String fetchedGroupId = getIntent().getStringExtra("fetchedGroupId");

                getUsers();

//                usersAdapterGroup = new UsersAdapterGroupAddMember(list, username, ChatAllChannels.this);
//                rv2.setAdapter(usersAdapterGroup);

//                userListProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//      Initialize RecyclerView adapter with an empty list
//        users = new ArrayList<>();
//        userGroupAdapter = new UserGroupAdapter(users);
//        userGroupAdapter = new UserGroupAdapter(users);
//        rv2.setAdapter(userGroupAdapter);

        // Set the group ID (you should obtain this from the selected group)
//        groupId = "kzWQuhH2vSVRkrNpvaISU28NTqQ2";

        // Load users from the group
//        loadGroupMembers();


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

    // Method to get the list of user IDs in a group
    public DatabaseReference getGroupMembersRef(String groupId) {
        return groupsRef.child(groupId).child("members");
    }

    // Method to retrieve user information
    public DatabaseReference getUserRef(String userId) {
        return usersRef.child(userId);
    }

    private void loadGroupMembers() {
        DatabaseReference groupMembersRef = getGroupMembersRef(groupId);

        groupMembersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();

                for (DataSnapshot memberIdSnapshot : dataSnapshot.getChildren()) {
                    String userId = memberIdSnapshot.getValue(String.class);

                    if (userId != null) {
                        // Fetch user information for each user ID
                        fetchUserInformation(userId);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void fetchUserInformation(String userId) {
        DatabaseReference userRef = getUserRef(userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user != null) {
                    users.add(user);
                    usersAdapterGroup.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors./gradlew build
            }
        });
    }


//Get user from database to display to the
    public void getUsers(){
        reference.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();

                if(!key.equals(user.getUid())){
                    list.add(key);
                    usersAdapterGroup.notifyDataSetChanged();
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