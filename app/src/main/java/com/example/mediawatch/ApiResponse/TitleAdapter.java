package com.example.mediawatch.ApiResponse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawatch.R;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder>{
//    private Project[] projects;
    private MediaWatch[] projects;
    public TitleAdapter(MediaWatch[] projects) {
        this.projects = projects;
    }
    @Override
    public int getItemCount() {
        return projects.length;
    }
    @NonNull
    @Override
    public TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_recycler_view, parent, false);
        return new TitleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TitleViewHolder holder, int position) {
        holder.bind(projects[position]);

    }
    static class TitleViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public TitleViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title_discover);
        }
        public void bind(MediaWatch project){
            if(project.category == "CHILD PROTECTION"){

            }
            title.setText(project.category);
        }

    }

}
