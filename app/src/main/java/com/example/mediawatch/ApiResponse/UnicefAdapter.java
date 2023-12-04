package com.example.mediawatch.ApiResponse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawatch.R;

public class UnicefAdapter extends RecyclerView.Adapter<UnicefAdapter.UnicefViewHolder>{
    private MediaWatch[] projects;
    public UnicefAdapter(MediaWatch[] projects) {
        this.projects = projects;
    }
    @Override
    public int getItemCount() {
        return projects.length;
    }
    @NonNull
    @Override
    public UnicefAdapter.UnicefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_news_feed, parent, false);
        return new UnicefAdapter.UnicefViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UnicefAdapter.UnicefViewHolder holder, int position) {
        holder.bind(projects[position]);
    }
    static class UnicefViewHolder extends RecyclerView.ViewHolder{
        ImageView discover_feed_image;
        TextView discover_feed_url;
        TextView discover_feed_title;
        TextView discover_feed_date, discover_feed_tonality;
        public UnicefViewHolder(@NonNull View itemView){
            super(itemView);
            discover_feed_title = itemView.findViewById(R.id.discover_feed_title);
            discover_feed_date = itemView.findViewById(R.id.discover_feed_date);
            discover_feed_url = itemView.findViewById(R.id.discover_feed_url);
//            discover_feed_tonality = itemView.findViewById(R.id.discover_feed_tonality);
//            discover_feed_image =
        }
        public void bind(MediaWatch project){
            discover_feed_title.setText(project.title);
            discover_feed_date.setText(project.date);
            discover_feed_url.setText(project.storyurl);
//            discover_feed_tonality.setText(project.tonality);
        }

    }


}

