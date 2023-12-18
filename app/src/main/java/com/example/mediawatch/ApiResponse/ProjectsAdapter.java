package com.example.mediawatch.ApiResponse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawatch.HomeAdapter;
import com.example.mediawatch.R;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>{
    private OnItemClickListener onItemClickListener;
    private Project[] projects;
    private MediaWatch[] mediaWatch;

//    public ProjectsAdapter(Project[] projects) {
//        this.projects = projects;
//    }

    public ProjectsAdapter(MediaWatch[] mediaWatch, OnItemClickListener onItemClickListener) {
        this.mediaWatch = mediaWatch;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
//        return projects.length;
        return mediaWatch.length;
    }
    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_main, parent, false);
        return new ProjectViewHolder(view, onItemClickListener);
    }
    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
//        holder.bind(projects[position]);
        MediaWatch item = mediaWatch[position];
        holder.bind(mediaWatch[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click and pass data
                onItemClickListener.onItemClick(item);
            }
        });

    }
    static class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        private TextView newsInfo;
//        private TextView newsDate;
        private TextView title;
        private TextView date;
        private TextView category;
        private TextView url;

        OnItemClickListener onItemClickListener;

        public ProjectViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener){
            super(itemView);
//            newsInfo = itemView.findViewById(R.id.newsData);
//            newsDate = itemView.findViewById(R.id.newsDate);
            title = itemView.findViewById(R.id.newsData);
            date = itemView.findViewById(R.id.newsDate);
            category = itemView.findViewById(R.id.category);
            url = itemView.findViewById(R.id.url);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }
//        public void bind(Project project){
//            newsInfo.setText(project.name);
//            newsDate.setText(project.description);
//        }
        public void bind(MediaWatch mediaWatch){
//            newsInfo.setText(mediaWatch.name);
//            newsDate.setText(mediaWatch.description);
            title.setText(mediaWatch.title);
            date.setText(mediaWatch.date);
            category.setText(mediaWatch.category);
//            url.setText(mediaWatch.storyurl);
        }

        @Override
        public void onClick(View v) {
//            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    // Define an interface for click events
    public interface OnItemClickListener {
        void onItemClick(MediaWatch item);
    }


}
