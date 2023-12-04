package com.example.mediawatch;

import static com.example.mediawatch.MainActivity.getMediaWatchArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediawatch.ApiResponse.MediaWatch;
import com.example.mediawatch.ApiResponse.Project;
import com.example.mediawatch.ApiResponse.ProjectsAdapter;
import com.example.mediawatch.ApiResponse.TitleAdapter;
import com.example.mediawatch.ApiResponse.UnicefAdapter;

import java.util.ArrayList;

public class Discover extends AppCompatActivity {

    private static final String TAG = "Discover";
    TextView category_discover_tv;
    RecyclerView category_discover_recycler;
    RecyclerView title, discoverNewsActivityRecycler, discover_feed_recycler;

    TextView discover_tonality, discover_tonality_all, discover_tonality_positive, discover_tonality_neutral, discover_tonality_negative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView healthTxt;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        healthTxt = findViewById(R.id.healthTxt);

        discover_tonality_all = findViewById(R.id.discover_tonality_all);
        discover_tonality_positive = findViewById(R.id.discover_tonality_positive);
        discover_tonality_neutral = findViewById(R.id.discover_tonality_neutral);
        discover_tonality_negative = findViewById(R.id.discover_tonality_negative);
        MediaWatch[] storedMediaWatchArray = getMediaWatchArray(Discover.this);
        Toast.makeText(Discover.this, "Hello "+storedMediaWatchArray.length, Toast.LENGTH_SHORT).show();
//        MediaWatch[] positive = {};
        ArrayList<MediaWatch> positive = new ArrayList<>();
        ArrayList<MediaWatch> negative = new ArrayList<>();
        ArrayList<MediaWatch> neutral = new ArrayList<>();
        RecyclerView discover_feed_recycler = findViewById(R.id.verticalTitleDiscoverNewsRecycler);
        UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
        discover_feed_recycler.setAdapter(unicefAdapter);

        for (MediaWatch element : storedMediaWatchArray){
            if (element.getTonality().equalsIgnoreCase("Positive")){
                positive.add(element);
            }else if (element.getTonality().equalsIgnoreCase("Negative")){
                negative.add(element);
            } else if(element.getTonality().equalsIgnoreCase("Neutral")){
                neutral.add(element);
            }
        }
        discover_tonality_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MediaWatch[] positiveMediaWatch = positive.toArray(positive.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
                discover_feed_recycler.setAdapter(unicefAdapter);

            }
        });
        discover_tonality_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Positive = "+positive.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] positiveMediaWatch = positive.toArray(positive.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });

        discover_tonality_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Negative = "+negative.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] positiveMediaWatch = negative.toArray(negative.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });

        discover_tonality_neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Neutral = "+neutral.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] positiveMediaWatch = neutral.toArray(neutral.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });







        Project[] project = {
                new Project("UNICEF"),
                new Project("EDUCATION"),
                new Project("PROTECTION"),
                new Project("OTHER"),
                new Project("MILWAUKEE")
        };
    }
}