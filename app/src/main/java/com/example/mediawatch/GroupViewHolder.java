package com.example.mediawatch;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupViewHolder extends RecyclerView.ViewHolder {
    private TextView groupNameTextView;

    public GroupViewHolder(@NonNull View itemView) {
        super(itemView);
        groupNameTextView = itemView.findViewById(R.id.chatRoomGroupNames);
    }

    public void bind(Group group) {
        groupNameTextView.setText(group.getGroupName());
    }
}

