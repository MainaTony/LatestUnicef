package com.example.mediawatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder>{
    Context context;
    ArrayList<News> arrayList;

    public MyAdapter(Context context, ArrayList<News> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_news_feed, parent, false);
        return new myViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        News news = arrayList.get(position);
        holder.newsHeadline.setText(news.newsHeading);
        holder.imageView.setImageResource(news.newsImage);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView newsHeadline;
        ImageView imageView;
        TextView newsDate;
        TextView newsUrl;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            newsDate = itemView.findViewById(R.id.date);
            newsUrl = itemView.findViewById(R.id.newsUrl);
            imageView = itemView.findViewById(R.id.newsImage);
            newsHeadline = itemView.findViewById(R.id.newsHeadline);

        }
    }
}
