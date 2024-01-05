//package com.example.mediawatch;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
//
//    private List<User> userList = new ArrayList<>();
//    private OnItemClickListener onItemClickListener;
//
//    public void addUser(User user) {
//        userList.add(user);
//        notifyDataSetChanged();
//    }
//
//    public void clear() {
//        userList.clear();
//        notifyDataSetChanged();
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.onItemClickListener = listener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(User selectedUser);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_user, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        final User user = userList.get(position);
//        holder.textViewUser.setText(user.getUsername());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(user);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return userList.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView textViewUser;
//
//        ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewUser = itemView.findViewById(R.id.displayNameTextView);
//        }
//    }
//}
//
//
//
