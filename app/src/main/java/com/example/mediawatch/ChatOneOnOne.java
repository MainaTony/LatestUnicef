package com.example.mediawatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
    TextView chatRoomGroupNames;

    private RecyclerView recyclerViewUsers, recyclerViewGroups;
//    private UserAdapter userAdapter;
    private GroupAdapter groupAdapter;
    private DatabaseReference usersRef, groupsRef, membersRef;
    private String adminUserId;

    ImageView chatOneOnOneImageViewBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_one_on_one);
        rv = findViewById(R.id.rv);
        chat_room_add_img = findViewById(R.id.chat_room_add_img);

//        recyclerView = findViewById(R.id.rv3);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        groups = new ArrayList<>();
//        groupAdapter = new GroupAdapter(groups);
//        recyclerView.setAdapter(groupAdapter);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");
        groupsRef = database.getReference("groups");
        membersRef = database.getReference("members");

        // Initialize RecyclerViews and Adapters
        recyclerViewUsers = findViewById(R.id.rv);
//        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
//        usersAdapter = new UsersAdapter();
//        recyclerViewUsers.setAdapter(usersAdapter);
//        adminUserId = "kzWQuhH2vSVRkrNpvaISU28NTqQ2";
//        This admin Id is for unicef
//        usersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                usersAdapter.clear();
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                    String userId = userSnapshot.getKey();
//                    String username = userSnapshot.child("username").getValue(String.class);
//                    User user = new User(userId, username);
//                    usersAdapter.addUser(user);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle errors
//            }
//        });
//
//        // Set up item click listener for adding users to a group
//        usersAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(User selectedUser) {
//                // Create or select a group and add the selected user as a member
//                createOrSelectGroup(selectedUser);
////                addUserToGroup(selectedUser);
//            }
//        });

//
        // Set up ValueEventListener to fetch user data
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            adminUserId = currentUser.getUid();
//        } else {
//            // Handle the case where the user is not authenticated
//        }
//        usersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                userAdapter.clear();
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                    String userId = userSnapshot.getKey();
//                    String username = userSnapshot.child("username").getValue(String.class);
//                    User user = new User(userId, username);
//                    userAdapter.addUser(user);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle errors
//            }
//        });
//
//        // Set up item click listener for adding users to a group
//        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(User selectedUser) {
//                // Create or select a group and add the selected user as a member
//                createOrSelectGroup(selectedUser);
//            }
//        });
        chatOneOnOneImageViewBack = findViewById(R.id.chatOneOnOneImageViewBack);
        chatOneOnOneImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatOneOnOne.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        chat_room_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(ChatOneOnOne.this, ChatCreateChannel.class);
//                startActivity(intent);
//                finish();

                // Check if the Slack app is installed on the device
//                PackageManager packageManager = getPackageManager();
//                Intent slackIntent = packageManager.getLaunchIntentForPackage("com.Slack");
//                if(slackIntent!= null){
//                    Uri slackUri = Uri.parse("slack://open");
//
//                    // Create an implicit intent
//                    Intent intent = new Intent(Intent.ACTION_VIEW, slackUri);
//                    // Start the activity
//                    startActivity(intent);
//                } else {
//                    // Redirect the user to the Play Store to install Slack
//                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
//                    playStoreIntent.setData(Uri.parse("market://details?id=com.Slack"));
//
//                    // Start the activity
//                    startActivity(playStoreIntent);
//                }

                // Replace "YOUR_SLACK_WEB_URL" with the actual URL of the Slack web portal
//                String slackWebUrl = "https://slack.com/get-started#/createnew";
//
//                // Create an Intent with the ACTION_VIEW action
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//
//                // Set the data (URL) for the Intent
//                intent.setData(Uri.parse(slackWebUrl));
//
//                // Start the activity
//                startActivity(intent);

                // URI for the Slack app
                Uri slackAppUri = Uri.parse("slack://");

                // Create an Intent with the VIEW action and the Slack URI
                Intent intent = new Intent(Intent.ACTION_VIEW, slackAppUri);

                // Start the activity
                startActivity(intent);


            }
        });

        userListProgressBar = findViewById(R.id.userListProgressBar);
        userListProgressBar.setVisibility(View.VISIBLE);

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

    public void getUsers() {
        reference.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();

                if (!key.equals(user.getUid())) {
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
//
//    private void fetchGroupsFromFirebase() {
//        DatabaseReference groupsRef = FirebaseDatabase.getInstance().getReference("groups");
//
//        groupsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                groups.clear();
//
//                for (DataSnapshot groupSnapshot : dataSnapshot.getChildren()) {
//                    Group group = groupSnapshot.getValue(Group.class);
//                    groups.add(group);
//                }
//
//                groupAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle errors
//            }
//        });
//    }

private void createOrSelectGroup(User selectedUser) {
    // Implement logic to create or select a group and add the selected user as a member
    // You may need to create a dialog or another UI component to manage this interaction
    // Example:
    String groupName = "Unicef"; // Replace with actual group name
    String groupId = groupsRef.push().getKey();
    List<String> members = new ArrayList<>();
    members.add(adminUserId);
    members.add(selectedUser.getUserId());

    Group group = new Group(groupId, groupName, adminUserId);
    groupsRef.child(groupId).setValue(group);
}

//    private void createOrSelectGroup(User selectedUser) {
//        // Implement logic to create or select a group and add the selected user as a member
//        // You may need to create a dialog or another UI component to manage this interaction
//        // Example:
//        String groupName = "Unicef"; // Replace with actual group name
//        String groupId = groupsRef.push().getKey();
//        List<String> members = new ArrayList<>();
//        members.add(adminUserId);
//        members.add(selectedUser.getUserId());
//
//        Group group = new Group(groupId, groupName, adminUserId);
//        groupsRef.child(groupId).setValue(group);
//
//        // Add the member to the membersRef
//        String memberName = "Isaiah"; // Replace with actual member name
//        String memberId = membersRef.push().getKey();
//        Member member = new Member(memberId, groupId, selectedUser.getUserId(), memberName);
//        membersRef.child(memberId).setValue(member);
//    }

    private void addUserToGroup(User selectedUser) {
        String groupName = "Unicef"; // Replace with the actual group name
        String groupId = groupsRef.push().getKey();

        // Create the group and add the admin and selected user as members
        Group group = new Group(groupId, groupName, adminUserId);
        groupsRef.child(groupId).setValue(group);

        List<String> members = new ArrayList<>();
        members.add(adminUserId);
        members.add(selectedUser.getUserId());
        DatabaseReference member = database.getReference("members");
        member.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (String memberId : members) {
                    String memberName = dataSnapshot.child(memberId).getValue(String.class);
                    if (memberName != null) {
                        // Add members to the membersRef
                        String memberKey = membersRef.push().getKey();
                        Member member = new Member(memberKey, groupId, memberId, memberName);
                        membersRef.child(memberKey).setValue(member);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });



    }
}
