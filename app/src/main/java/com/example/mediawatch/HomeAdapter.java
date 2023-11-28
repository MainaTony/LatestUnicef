package com.example.mediawatch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawatch.ApiResponse.DataApiResponse;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private DataApiResponse[] dataApiResponse;

    public HomeAdapter(DataApiResponse[] dataApiResponse) {
        this.dataApiResponse = dataApiResponse;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_main, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(dataApiResponse[position]);

    }

    @Override
    public int getItemCount() {
        return dataApiResponse.length;
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView time;
        private TextView date;
        private TextView title;
        private TextView media;
        private TextView mediaType;
        private TextView summary;
        private TextView duration;
        private TextView personality, amount, fileUrl, storyUrl, prominence, tonality, sector, subSector, company, company_id, theme, key_headline, category, date_created;
        public HomeViewHolder(@NonNull View itemview){
            super(itemview);
//            imageView = itemview.findViewById(R.id.newsImage);
            title = itemview.findViewById(R.id.newsData);
            date = itemview.findViewById(R.id.newsDate);
//
//            theme = itemview.findViewById(R.id.theme);
//            storyUrl = itemview.findViewById(R.id.newsUrl);
        }

        public void bind(DataApiResponse dataApiResponse){
//            imageView.setImageResource(d);
            title.setText(dataApiResponse.title);
            date.setText(dataApiResponse.date);
//            theme.setText(dataApiResponse.theme);
//            storyUrl.setText(dataApiResponse.storyUrl);
        }
    }
}
