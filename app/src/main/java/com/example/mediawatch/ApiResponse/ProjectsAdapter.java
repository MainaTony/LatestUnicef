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
    private Project[] projects;

    public ProjectsAdapter(Project[] projects) {
        this.projects = projects;
    }

    @Override
    public int getItemCount() {
        return projects.length;
    }
    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_main, parent, false);
        return new ProjectViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(projects[position]);

    }
    static class ProjectViewHolder extends RecyclerView.ViewHolder{

        private TextView newsInfo;
        private TextView newsDate;
        public ProjectViewHolder(@NonNull View itemView){
            super(itemView);
            newsInfo = itemView.findViewById(R.id.newsData);
            newsDate = itemView.findViewById(R.id.newsDate);
        }
        public void bind(Project project){
            newsInfo.setText(project.name);
            newsDate.setText(project.description);
        }
    }

}
