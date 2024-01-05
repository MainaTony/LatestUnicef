//package com.example.mediawatch;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
//
//    private List<Member> memberList = new ArrayList<>();
//    private Context context;
//
//    public MemberAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void addMember(Member member) {
//        memberList.add(member);
//        notifyDataSetChanged();
//    }
//
//    public void clear() {
//        memberList.clear();
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_member, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Member member = memberList.get(position);
//
//        // Bind member data to the ViewHolder
//        holder.memberNameTextView.setText(member.getMemberName());
//        holder.memberIdTextView.setText("Member ID: " + member.getMemberId());
//    }
//
//    @Override
//    public int getItemCount() {
//        return memberList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView memberNameTextView;
//        TextView memberIdTextView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            memberNameTextView = itemView.findViewById(R.id.memberNameTextView);
//            memberIdTextView = itemView.findViewById(R.id.memberIdTextView);
//        }
//    }
//}
//
