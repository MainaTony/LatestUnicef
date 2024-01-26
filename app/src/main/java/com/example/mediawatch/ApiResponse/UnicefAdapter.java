package com.example.mediawatch.ApiResponse;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.ui.PlayerView;
//import com.google.android.exoplayer2.ui.PlayerControlView;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawatch.Discover;
import com.example.mediawatch.R;

public class UnicefAdapter extends RecyclerView.Adapter<UnicefAdapter.UnicefViewHolder>{
    private MediaWatch[] projects;
    Context context;
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
        TextView read_pdf;
        TextView discover_feed_date, discover_feed_tonality;

//        VideoView discoverVideo;

//        SimpleExoPlayer player;
        PlayerView playerView;

        public UnicefViewHolder(@NonNull View itemView){
            super(itemView);
            discover_feed_title = itemView.findViewById(R.id.discover_feed_title);
            discover_feed_date = itemView.findViewById(R.id.discover_feed_date);
            discover_feed_url = itemView.findViewById(R.id.discover_feed_url);
            playerView = itemView.findViewById(R.id.discoverVideo);
            read_pdf = itemView.findViewById(R.id.read_pdf);

        }
        public void bind(MediaWatch project){
            discover_feed_title.setText(project.title);
            discover_feed_date.setText(project.date);



            if(!project.storyurl.isEmpty()){
                discover_feed_url.setText("Read More");
                playerView.setVisibility(View.GONE);

            }
            discover_feed_url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = project.storyurl;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    itemView.getContext().startActivity(intent);
                }
            });



            if(project.fileurl.toLowerCase().endsWith(".pdf")){
                playerView.setVisibility(View.GONE);
                read_pdf.setVisibility(View.VISIBLE);
//                read_pdf.setText(project.fileurl);
                read_pdf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(project.fileurl), "application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        itemView.getContext().startActivity(intent);

                    }
                });
            }


            ExoPlayer player = new ExoPlayer.Builder(itemView.getContext()).build();
            // Bind the player to the view.
            playerView.setPlayer(player);
            // Build the media item.
            MediaItem mediaItem = MediaItem.fromUri(project.fileurl);
            // Set the media item to be played.
            player.setMediaItem(mediaItem);
            // Set to false to avoid auto-play
            player.setPlayWhenReady(false);
            // Prepare the player.
            player.prepare();
            // Start the playback.
//            player.play();




        }

    }


}

