package com.example.mediawatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapterGroup extends RecyclerView.Adapter<UsersAdapterGroup.ViewHolder> {

    List<String> userList;
    String username;
    Context mContext;
    FirebaseDatabase database;
    DatabaseReference reference;

    String fetchedGroupId;
    FirebaseAuth auth;

    String id;
    List<Member> selectedMembers;
//    String currentAdmin;

    public UsersAdapterGroup(List<String> userList, String userName, Context mContext) {
        this.userList = userList;
        this.username = userName;
        this.mContext = mContext;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_add_group, parent,false);
//        Bundle bundle = new Bundle();
//        id = bundle.getString("groupId");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        reference.child("Users").child(userList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                final String otherName = snapshot.child("username").getValue().toString();

                Member myMember = new Member();
                myMember.setMemberName(otherName);


//                String imageURL = snapshot.child("image").getValue().toString();

                holder.textViewUsers.setText(otherName);

//                if (imageURL.equals("null"))
//                {
//                    holder.imageViewUsers.setImageResource(R.drawable.account);
//                }
//                else
//                {
//                    holder.imageViewUsers.setImageResource(R.drawable.account);
//                    //Picasso.get().load(imageURL).into(holder.imageViewUsers);
//                }

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Group group = new Group();


//                        reference.child("members").child(group.getGroupId()).child(otherName).setValue(otherName);
//                        membersRef.child(group.getGroupId()).child(adminMember.getMemberId()).setValue(adminMember);

//                        Intent intent = new Intent(mContext,MyChatActivity.class);
//                        intent.putExtra("username",username);
//                        intent.putExtra("otherName",otherName);
//                        mContext.startActivity(intent);
//                        Member member = new Member();
//                        member.setMemberName(otherName);
//                        reference = reference.child("members");
//                        Intent intent = new Intent(mContext, ChatCreateChannel.class);
//                        String groupAdmin = intent.getStringExtra("groupAdmin");
//                        reference.child(currentAdmin).child(member.getMemberId()).setValue(member);



                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewUsers;
        private CircleImageView imageViewUsers;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUsers = itemView.findViewById(R.id.textViewUsersGroup);
            imageViewUsers = itemView.findViewById(R.id.imageViewUsersGroup);
            cardView = itemView.findViewById(R.id.cardViewGroup);
        }
    }

//    private void populateMemberList() {
//        // Assume you have a method to fetch and populate memberList from your data source
//        // Replace this with your actual data retrieval logic
//
//        // For example, let's say you have a method called fetchMembersFromDataSource()
//        // that retrieves members from your data source (e.g., Firebase Realtime Database)
//
//        // Here's a hypothetical example:
//
//        DatabaseReference membersRef = databaseReference.child("Users");
//
//        membersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                memberList.clear();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Member member = snapshot.getValue(Member.class);
//                    memberList.add(member);
//                }
//
//                // Notify the adapter that the data set has changed
//                memberAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//    }
}
