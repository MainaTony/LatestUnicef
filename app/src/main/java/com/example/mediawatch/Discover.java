package com.example.mediawatch;

import static com.example.mediawatch.MainActivity.getMediaWatchArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediawatch.ApiResponse.MediaWatch;
import com.example.mediawatch.ApiResponse.Project;
import com.example.mediawatch.ApiResponse.ProjectsAdapter;
import com.example.mediawatch.ApiResponse.TitleAdapter;
import com.example.mediawatch.ApiResponse.UnicefAdapter;

import java.util.ArrayList;

public class Discover extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Discover";
    TextView category_discover_tv;
    RecyclerView category_discover_recycler;
    RecyclerView title, discoverNewsActivityRecycler, discover_feed_recycler;

    TextView discover_tonality, discover_tonality_all, discover_tonality_positive, discover_tonality_neutral, discover_tonality_negative;

    TextView discover_print_media, discover_tv, discover_radio, discover_online_media, discover_media_type_all;

    TextView discover_category_unicef_online_media, discover_category_governance, discover_category_child_protection, discover_category_child_health, discover_category_unicef, discover_category_child_education, discover_category_others;
    ArrayList<MediaWatch> combined = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView healthTxt;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
//        healthTxt = findViewById(R.id.healthTxt);

//        Media Type Section binding
        discover_print_media = findViewById(R.id.discover_print_media);
        discover_tv = findViewById(R.id.discover_tv);
        discover_radio = findViewById(R.id.discover_radio);
        discover_online_media = findViewById(R.id.discover_online_media);
        discover_media_type_all = findViewById(R.id.discover_media_type_all);


//        Tonality Section binding
        discover_tonality_all = findViewById(R.id.discover_tonality_all);
        discover_tonality_positive = findViewById(R.id.discover_tonality_positive);
        discover_tonality_neutral = findViewById(R.id.discover_tonality_neutral);
        discover_tonality_negative = findViewById(R.id.discover_tonality_negative);

//        Category Section binding
        discover_category_unicef_online_media = findViewById(R.id.discover_category_unicef_online_media);
        discover_category_governance = findViewById(R.id.discover_category_governance);
        discover_category_child_protection = findViewById(R.id.discover_category_child_protection);
        discover_category_child_health = findViewById(R.id.discover_category_child_health);
        discover_category_unicef = findViewById(R.id.discover_category_unicef);
        discover_category_child_education = findViewById(R.id.discover_category_child_education);
        discover_category_others = findViewById(R.id.discover_category_others);

        MediaWatch[] storedMediaWatchArray = getMediaWatchArray(Discover.this);
//        Toast.makeText(Discover.this, "Hello "+storedMediaWatchArray.length, Toast.LENGTH_SHORT).show();

//        Tonality
        final ArrayList<MediaWatch> positive = new ArrayList<>();
        ArrayList<MediaWatch> negative = new ArrayList<>();
        ArrayList<MediaWatch> neutral = new ArrayList<>();

//        Media Type
        ArrayList<MediaWatch> printMedia = new ArrayList<>();
        ArrayList<MediaWatch> tv = new ArrayList<>();
        ArrayList<MediaWatch> radio = new ArrayList<>();
        ArrayList<MediaWatch> onlineMedia = new ArrayList<>();
        ArrayList<MediaWatch> allMediaType = new ArrayList<>();

//        Category
        ArrayList<MediaWatch> others = new ArrayList<>();
        ArrayList<MediaWatch> childEducation = new ArrayList<>();
        ArrayList<MediaWatch> unicef = new ArrayList<>();
        ArrayList<MediaWatch> childHealth = new ArrayList<>();
        ArrayList<MediaWatch> childProtection = new ArrayList<>();
        ArrayList<MediaWatch> governance = new ArrayList<>();
        ArrayList<MediaWatch> unicefOnlineMedia = new ArrayList<>();

        RecyclerView discover_feed_recycler = findViewById(R.id.verticalTitleDiscoverNewsRecycler);
        UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
        discover_feed_recycler.setAdapter(unicefAdapter);

//        Sorting tonality section
        for (MediaWatch element : storedMediaWatchArray){
            if (element.getTonality().equalsIgnoreCase("Positive")){
                positive.add(element);
            }else if (element.getTonality().equalsIgnoreCase("Negative")){
                negative.add(element);
            } else if(element.getTonality().equalsIgnoreCase("Neutral")){
                neutral.add(element);
            }
        }

        //        Sorting Media Type section
        for (MediaWatch element : storedMediaWatchArray){
            if (element.getMediatype().equalsIgnoreCase("Online Media")){
                onlineMedia.add(element);
            }else if (element.getMediatype().equalsIgnoreCase("Radio")){
                radio.add(element);
            } else if(element.getMediatype().equalsIgnoreCase("TV")){
                tv.add(element);
            } else if(element.getMediatype().equalsIgnoreCase("Print Media")){
                printMedia.add(element);
            }
        }

        //        Sorting Category section
        for (MediaWatch element : storedMediaWatchArray){
            if (element.getCategory().equalsIgnoreCase("Others")){
                others.add(element);
            }else if (element.getCategory().equalsIgnoreCase("Child Education")){
                childEducation.add(element);
            } else if(element.getCategory().equalsIgnoreCase("UNICEF")){
                unicef.add(element);
            } else if(element.getCategory().equalsIgnoreCase("CHILD HEALTH")){
                childHealth.add(element);
            } else if(element.getCategory().equalsIgnoreCase("CHILD PROTECTION")){
                childProtection.add(element);
            } else if(element.getCategory().equalsIgnoreCase("UNICEF ONLINE MEDIA")){
                unicefOnlineMedia.add(element);
            } else if(element.getCategory().equalsIgnoreCase("GOVERNANCE")){
                governance.add(element);
            }
        }

        discover_category_unicef_online_media.setOnClickListener(this);
        discover_category_governance.setOnClickListener(this);
        discover_category_child_protection.setOnClickListener(this);
        discover_category_child_health.setOnClickListener(this);
        discover_category_unicef.setOnClickListener(this);
        discover_category_child_education.setOnClickListener(this);
        discover_category_others.setOnClickListener(this);
//        discover_category_unicef_online_media.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] onlineMediaWatch = unicefOnlineMedia.toArray(unicefOnlineMedia.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(onlineMediaWatch);
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_governance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] governanceMediaWatch = governance.toArray(governance.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(governanceMediaWatch);
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_child_protection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] childProtectionMediaWatch = childProtection.toArray(childProtection.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(childProtectionMediaWatch);
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_child_health.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] childHealthMediaWatch = childHealth.toArray(childHealth.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(childHealthMediaWatch);
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_unicef.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] unicefMediaWatch = unicef.toArray(unicef.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(unicefMediaWatch);
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_child_education.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                discover_category_child_education.setBackgroundResource(R.color.brand_color);
//                discover_category_child_education.setTextColor(Color.parseColor("#FFFFFF"));
//                MediaWatch[] childMediaWatch = childEducation.toArray(childEducation.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(childMediaWatch);
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });

//        discover_category_others.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                discover_category_others.setBackgroundResource(R.color.brand_color);
//                discover_category_others.setTextColor(Color.parseColor("#FFFFFF"));
//                MediaWatch[] othersMediaWatch = others.toArray(others.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(othersMediaWatch);
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });




//        Media Type On Click Listeners
        discover_print_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Print Media = "+printMedia.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] printMediaWatch = printMedia.toArray(printMedia.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(printMediaWatch);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "TV = "+tv.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] tvMediaWatch = tv.toArray(tv.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(tvMediaWatch);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Radio = "+radio.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] radioMediaWatch = radio.toArray(radio.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(radioMediaWatch);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_online_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Online Media = "+onlineMedia.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] onlineMediaWatch = onlineMedia.toArray(onlineMedia.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(onlineMediaWatch);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_media_type_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });

//        Click Listeners
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

    public static void displayResult(){
        System.out.print("Final result");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.discover_category_unicef_online_media:
//                Toast.makeText(Discover.this, "TV = "+positive.size(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Unicef Online Media Clicked", Toast.LENGTH_SHORT).show();
                break;


            default:

                break;
        }
    }
}