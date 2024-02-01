package com.example.mediawatch;

import static com.example.mediawatch.MainActivity.getMediaWatchArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintDocumentAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mediawatch.ApiResponse.MediaWatch;
import com.example.mediawatch.ApiResponse.Project;
import com.example.mediawatch.ApiResponse.ProjectsAdapter;
import com.example.mediawatch.ApiResponse.TitleAdapter;
import com.example.mediawatch.ApiResponse.UnicefAdapter;
//import com.itextpdf.html2pdf.HtmlConverter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//Save Document imports
//import android.os.Environment;
////import com.itextpdf.html2pdf.HtmlConverter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import com.itextpdf.html2pdf.HtmlConverter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;




public class Discover extends AppCompatActivity  {
//    implements View.OnClickListener
    private static final String TAG2 = "HtmlToPdfActivity";
    private static final String TAG = "Discover";
    TextView category_discover_tv;
    RecyclerView category_discover_recycler;
    RecyclerView title, discoverNewsActivityRecycler, discover_feed_recycler;

    TextView discover_tonality, discover_tonality_all, discover_tonality_positive, discover_tonality_neutral, discover_tonality_negative;

    TextView discover_print_media, discover_tv, discover_radio, discover_online_media, discover_media_type_all;

    TextView discover_category_unicef_online_media, discover_category_governance, discover_category_child_protection, discover_category_child_health, discover_category_unicef, discover_category_child_education, discover_category_others;
    ArrayList<MediaWatch> combined = new ArrayList<>();
    HorizontalScrollView tonalityHorizontalScrollView;
    LinearLayout tonalityLinearLayout, thematicLinearLayout;

    ImageView discoverImageViewBack;

    VideoView discoverVideo;

    TextView customSpinnerItem;

    CardView unicefCardView, governanceCardView, childProtectionCardView, childHealthCardView, unicef2CardView, childEducationCardView, othersCardView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView healthTxt;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        discoverImageViewBack = findViewById(R.id.discoverImageViewBack);


        discoverImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Discover.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

//        Media Type Section binding
//        discover_print_media = findViewById(R.id.discover_print_media);
//        discover_tv = findViewById(R.id.discover_tv);
//        discover_radio = findViewById(R.id.discover_radio);
//        discover_online_media = findViewById(R.id.discover_online_media);
//        discover_media_type_all = findViewById(R.id.discover_media_type_all);
//        tonalityLinearLayout = findViewById(R.id.tonalityLinearLayout);
//        thematicLinearLayout = findViewById(R.id.thematicLinearLayout);



//        Horizontal Scroll views
//        tonalityHorizontalScrollView = findViewById(R.id.tonalityHorizontalScrollView);


//        Tonality Section binding
//        discover_tonality_all = findViewById(R.id.discover_tonality_all);
//        discover_tonality_positive = findViewById(R.id.discover_tonality_positive);
//        discover_tonality_neutral = findViewById(R.id.discover_tonality_neutral);
//        discover_tonality_negative = findViewById(R.id.discover_tonality_negative);

//        Category Section binding
//        discover_category_unicef_online_media = findViewById(R.id.discover_category_unicef_online_media);
//        discover_category_governance = findViewById(R.id.discover_category_governance);
//        discover_category_child_protection = findViewById(R.id.discover_category_child_protection);
//        discover_category_child_health = findViewById(R.id.discover_category_child_health);
//        discover_category_unicef = findViewById(R.id.discover_category_unicef);
//        discover_category_child_education = findViewById(R.id.discover_category_child_education);
//        discover_category_others = findViewById(R.id.discover_category_others);

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
        for (MediaWatch element : storedMediaWatchArray) {
            if (element.getTonality().equalsIgnoreCase("Positive")) {
                positive.add(element);
            } else if (element.getTonality().equalsIgnoreCase("Negative")) {
                negative.add(element);
            } else if (element.getTonality().equalsIgnoreCase("Neutral")) {
                neutral.add(element);
            }
        }

        //        Sorting Media Type section
        for (MediaWatch element : storedMediaWatchArray) {
            if (element.getMediatype().equalsIgnoreCase("Online Media")) {
                onlineMedia.add(element);
            } else if (element.getMediatype().equalsIgnoreCase("Radio")) {
                radio.add(element);
            } else if (element.getMediatype().equalsIgnoreCase("TV")) {
                tv.add(element);
            } else if (element.getMediatype().equalsIgnoreCase("Print Media")) {
                printMedia.add(element);
            }
        }

        //        Sorting Category section
        for (MediaWatch element : storedMediaWatchArray) {
            if (element.getCategory().equalsIgnoreCase("Others")) {
                others.add(element);
            } else if (element.getCategory().equalsIgnoreCase("Child Education")) {
                childEducation.add(element);
            } else if (element.getCategory().equalsIgnoreCase("UNICEF")) {
                unicef.add(element);
            } else if (element.getCategory().equalsIgnoreCase("CHILD HEALTH")) {
                childHealth.add(element);
            } else if (element.getCategory().equalsIgnoreCase("CHILD PROTECTION")) {
                childProtection.add(element);
            } else if (element.getCategory().equalsIgnoreCase("UNICEF ONLINE MEDIA")) {
                unicefOnlineMedia.add(element);
            } else if (element.getCategory().equalsIgnoreCase("GOVERNANCE")) {
                governance.add(element);
            }
        }

//        Thematic Areas Spinner
        Spinner spinner = findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.thematic_areas, // Array resource containing your items
                R.layout.spinner_layout
        );


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.drop_down_item);

//        adapter.insert("Theme", 0);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle item selection
                String selectedValue = (String) parentView.getSelectedItem();
                // Do something with the selected value

                if(selectedValue.equalsIgnoreCase("Unicef Online Media")){

                    MediaWatch[] onlineMediaWatch = unicefOnlineMedia.toArray(unicefOnlineMedia.toArray(new MediaWatch[0]));
                    UnicefAdapter onlineMediaWatchAdapter = new UnicefAdapter(onlineMediaWatch);
//                    handleElementClick(selectedItemView);
//                    discover_category_unicef_online_media.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(onlineMediaWatchAdapter);
//                    Toast.makeText(Discover.this, "1", Toast.LENGTH_SHORT).show();

                } else if(selectedValue.equalsIgnoreCase("Child Protection")){

//                    thematicLinearLayout.setBackgroundResource(R.color.transparent);
                    MediaWatch[] childProtectionMediaWatch = childProtection.toArray(childProtection.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(childProtectionMediaWatch);
//                    handleElementClick(selectedItemView);
//                    discover_category_child_protection.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);
//                    Toast.makeText(Discover.this, "2", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("Child Health")){

                    MediaWatch[] childHealthMediaWatch = childHealth.toArray(childHealth.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(childHealthMediaWatch);
//                    handleElementClick(selectedItemView);
//                    discover_category_child_health.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "3", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("Unicef")){

                    MediaWatch[] unicefMediaWatch = unicef.toArray(unicef.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(unicefMediaWatch);
//                    handleElementClick(selectedItemView);
//                    discover_category_unicef.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "4", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("Child Education")){

                    MediaWatch[] childMediaWatch = childEducation.toArray(childEducation.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(childMediaWatch);
//                    handleElementClick(selectedItemView);
//                    discover_category_child_education.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "5", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("Others")){

                    MediaWatch[] othersMediaWatch = others.toArray(others.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(othersMediaWatch);
//                    handleElementClick(selectedItemView);
//                    discover_category_others.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "6", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(Discover.this, "Null element", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected if needed
            }
        });

        //        Media Type Spinner
        Spinner mediaTypeSpinner = findViewById(R.id.mediaTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> mediaAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.media_type, // Array resource containing your items
                R.layout.media_spinner_layout
        );


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.media_drop_down_item);
        // Apply the adapter to the spinner
        mediaTypeSpinner.setAdapter(mediaAdapter);

        mediaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle item selection
                String selectedValue = (String) parentView.getSelectedItem();
                // Do something with the selected value

                if(selectedValue.equalsIgnoreCase("Online Media")){

                    MediaWatch[] onlineMediaWatch = onlineMedia.toArray(onlineMedia.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(onlineMediaWatch);
//                    handleMediaElementClick(selectedItemView);
//                    discover_online_media.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);


//                    Toast.makeText(Discover.this, "1", Toast.LENGTH_SHORT).show();

                } else if(selectedValue.equalsIgnoreCase("Print Media")){

                    MediaWatch[] printMediaWatch = printMedia.toArray(printMedia.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(printMediaWatch);
//                    handleMediaElementClick(selectedItemView);
//                    discover_print_media.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "2", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("Radio")){

                    MediaWatch[] radioMediaWatch = radio.toArray(radio.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(radioMediaWatch);
//                    handleMediaElementClick(selectedItemView);
//                    discover_radio.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);


//                    Toast.makeText(Discover.this, "3", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("Tv")){

                    MediaWatch[] tvMediaWatch = tv.toArray(tv.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(tvMediaWatch);
//                    handleMediaElementClick(selectedItemView);
//                    discover_tv.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);


//                    Toast.makeText(Discover.this, "4", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("All")){

                    UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
//                    handleMediaElementClick(selectedItemView);
//                    discover_media_type_all.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "5", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Discover.this, "Null element", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected if needed
            }
        });















        //        Tonality Type Spinner
        Spinner tonalitySpinner = findViewById(R.id.tonalitySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> tonalityAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tonality, // Array resource containing your items
                R.layout.tonality_media_spinner_layout
        );


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.tonality_drop_down_item);
        // Apply the adapter to the spinner
        tonalitySpinner.setAdapter(tonalityAdapter);
        tonalitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle item selection
                String selectedValue = (String) parentView.getSelectedItem();
                // Do something with the selected value

                if(selectedValue.equalsIgnoreCase("Positive")){
                    MediaWatch[] positiveMediaWatch = positive.toArray(positive.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
//                    handleTonalityElementClick(selectedItemView);
//                    discover_tonality_positive.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "1", Toast.LENGTH_SHORT).show();

                } else if(selectedValue.equalsIgnoreCase("Neutral")){

                    MediaWatch[] positiveMediaWatch = neutral.toArray(neutral.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
//                    handleTonalityElementClick(selectedItemView);
//                    discover_tonality_neutral.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "2", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("Negative")){

                    MediaWatch[] positiveMediaWatch = negative.toArray(negative.toArray(new MediaWatch[0]));
                    UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
//                    handleTonalityElementClick(selectedItemView);
//                    discover_tonality_negative.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);

//                    Toast.makeText(Discover.this, "3", Toast.LENGTH_SHORT).show();
                } else if(selectedValue.equalsIgnoreCase("All")){

                    UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
//                    handleTonalityElementClick(v);
//                    discover_tonality_all.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
                    discover_feed_recycler.setAdapter(unicefAdapter);


//                    Toast.makeText(Discover.this, "5", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(Discover.this, "Null element", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected if needed
            }
        });





//        discover_category_unicef_online_media.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] onlineMediaWatch = unicefOnlineMedia.toArray(unicefOnlineMedia.toArray(new MediaWatch[0]));
//                UnicefAdapter onlineMediaWatchAdapter = new UnicefAdapter(onlineMediaWatch);
//                handleElementClick(v);
////                discover_category_unicef_online_media.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(onlineMediaWatchAdapter);
//
//
//            }
//        });
//        discover_category_governance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] governanceMediaWatch = governance.toArray(governance.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(governanceMediaWatch);
//                handleElementClick(v);
//                discover_category_governance.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_child_protection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                thematicLinearLayout.setBackgroundResource(R.color.transparent);
//                MediaWatch[] childProtectionMediaWatch = childProtection.toArray(childProtection.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(childProtectionMediaWatch);
//                handleElementClick(v);
//                discover_category_child_protection.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_child_health.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] childHealthMediaWatch = childHealth.toArray(childHealth.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(childHealthMediaWatch);
//                handleElementClick(v);
//                discover_category_child_health.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_unicef.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaWatch[] unicefMediaWatch = unicef.toArray(unicef.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(unicefMediaWatch);
//                handleElementClick(v);
//                discover_category_unicef.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_category_child_education.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                discover_category_child_education.setBackgroundResource(R.color.brand_color);
////                discover_category_child_education.setTextColor(Color.parseColor("#FFFFFF"));
//                MediaWatch[] childMediaWatch = childEducation.toArray(childEducation.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(childMediaWatch);
//                handleElementClick(v);
//                discover_category_child_education.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//
//        discover_category_others.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                discover_category_others.setBackgroundResource(R.color.brand_color);
////                discover_category_others.setTextColor(Color.parseColor("#FFFFFF"));
//                MediaWatch[] othersMediaWatch = others.toArray(others.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(othersMediaWatch);
//                handleElementClick(v);
//                discover_category_others.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });


//        Media Type On Click Listeners
//        discover_print_media.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(Discover.this, "Print Media = "+printMedia.size(), Toast.LENGTH_SHORT).show();
//                MediaWatch[] printMediaWatch = printMedia.toArray(printMedia.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(printMediaWatch);
////                handleMediaElementClick(v);
//                discover_print_media.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(Discover.this, "TV = "+tv.size(), Toast.LENGTH_SHORT).show();
//                MediaWatch[] tvMediaWatch = tv.toArray(tv.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(tvMediaWatch);
////                handleMediaElementClick(v);
//                discover_tv.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_radio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(Discover.this, "Radio = "+radio.size(), Toast.LENGTH_SHORT).show();
//                MediaWatch[] radioMediaWatch = radio.toArray(radio.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(radioMediaWatch);
////                handleMediaElementClick(v);
//                discover_radio.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_online_media.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(Discover.this, "Online Media = "+onlineMedia.size(), Toast.LENGTH_SHORT).show();
//                MediaWatch[] onlineMediaWatch = onlineMedia.toArray(onlineMedia.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(onlineMediaWatch);
////                handleMediaElementClick(v);
//                discover_online_media.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });
//        discover_media_type_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
////                handleMediaElementClick(v);
//                discover_media_type_all.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });

//        Click Listeners
//        discover_tonality_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                MediaWatch[] positiveMediaWatch = positive.toArray(positive.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
////                handleTonalityElementClick(v);
////                discover_tonality_all.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//
//            }
//        });
//        discover_tonality_positive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(Discover.this, "Positive = "+positive.size(), Toast.LENGTH_SHORT).show();
//                MediaWatch[] positiveMediaWatch = positive.toArray(positive.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
////                handleTonalityElementClick(v);
////                discover_tonality_positive.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });

//        discover_tonality_negative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(Discover.this, "Negative = "+negative.size(), Toast.LENGTH_SHORT).show();
//                MediaWatch[] positiveMediaWatch = negative.toArray(negative.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
////                handleTonalityElementClick(v);
////                discover_tonality_negative.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });

//        discover_tonality_neutral.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(Discover.this, "Neutral = "+neutral.size(), Toast.LENGTH_SHORT).show();
//                MediaWatch[] positiveMediaWatch = neutral.toArray(neutral.toArray(new MediaWatch[0]));
//                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
////                handleTonalityElementClick(v);
////                discover_tonality_neutral.setTextColor(ContextCompat.getColor(Discover.this, R.color.white));
//                discover_feed_recycler.setAdapter(unicefAdapter);
//            }
//        });

        Project[] project = {
                new Project("UNICEF"),
                new Project("EDUCATION"),
                new Project("PROTECTION"),
                new Project("OTHER"),
                new Project("MILWAUKEE")
        };
    }

    public static void displayResult() {
        System.out.print("Final result");
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.discover_category_unicef_online_media:
////                Toast.makeText(Discover.this, "TV = "+positive.size(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(this, "Unicef Online Media Clicked", Toast.LENGTH_SHORT).show();
//                discover_category_child_education.setBackgroundResource(R.color.brand_color);
//                break;
//            default:
//                break;
//        }
//    }
//    Download Implementation
//public static void convert(String htmlContent, String outputPdfPath) {
//    try {
//        // Create a File object for the output PDF
//        File pdfFile = new File(outputPdfPath);
//
//        // Create output stream for the PDF
//        FileOutputStream fos = new FileOutputStream(pdfFile);
//
//        // Convert HTML to PDF
//        HtmlConverter.convertToPdf(htmlContent, fos);
//
//        // Close the output stream
//        fos.close();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }

//    public static void saveFileToDownloads(String fileName, String content) {
//        // Check if external storage is available
//        if (isExternalStorageWritable()) {
//            // Get the Downloads directory
//            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//
//            // Create a File object for the new file in the Downloads directory
//            File file = new File(downloadsDir, fileName);
//
//            try {
//                // Create FileWriter and write content to the file
//                FileWriter writer = new FileWriter(file);
//                writer.write(content);
//                writer.flush();
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static boolean isExternalStorageWritable() {
//        String state = Environment.getExternalStorageState();
//        return Environment.MEDIA_MOUNTED.equals(state);
//    }

//    public static void convert(String htmlContent, String outputPdfPath) {
//        try {
//            // Create a File object for the output PDF
//            File pdfFile = new File(outputPdfPath);
//
//            // Create output stream for the PDF
//            FileOutputStream fos = new FileOutputStream(pdfFile);
//
//            // Convert HTML to PDF
//            HtmlConverter.convertToPdf(htmlContent, fos);
//
//            // Close the output stream
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void convertUsingHtmlConverter(String htmlContent, String outputPdfPath) {
//        try {
//            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPdfPath));
//            System.out.println("PDF created successfully at: " + outputPdfPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    External OnClick Method
//private void handleElementClick(View view) {
//        Boolean selected = false;
//    // Handle the click event for each element
//    if (view.getId() == R.id.discover_category_governance) {
////        Toast.makeText(getApplicationContext(), "Button 1 Clicked", Toast.LENGTH_SHORT).show();
//        int childCount = ((LinearLayout) discover_category_governance.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_governance.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
//                // Reset background color to transparent
//                childView.setBackgroundResource(R.drawable.rounded_background);
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//            discover_category_governance.setBackgroundResource(R.drawable.rounded_background_clicked);
//
////        discover_tonality_all.setBackgroundResource(R.color.brand_color);
//    } else if (view.getId() == R.id.discover_category_unicef_online_media) {
//        int childCount = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
//                childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//        discover_category_unicef_online_media.setBackgroundResource(R.drawable.rounded_background_clicked);
////        discover_tonality_positive.setBackgroundResource(R.color.brand_color);
////        discover_tonality_positive.setText("Clicked");
//    } else if (view.getId() == R.id.discover_category_unicef_online_media) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//        int childCount = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
//                childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//        discover_category_unicef_online_media.setBackgroundResource(R.drawable.rounded_background_clicked);
////        discover_category_unicef_online_media.setTextColor(ContextCompat.getColor(this, R.color.white));
////        discover_category_unicef_online_media.setTextColor(getResources().getColor(R.color.white));
//
//    }
//    // Add more cases as needed
//    else if (view.getId() == R.id.discover_category_child_protection) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//        int childCount = ((LinearLayout) discover_category_child_protection.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_child_protection.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
//                childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//        discover_category_child_protection.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//    }
//
//    else if (view.getId() == R.id.discover_category_child_health) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//        int childCount = ((LinearLayout) discover_category_child_health.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_child_health.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
//                childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//        discover_category_child_health.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//    }
//    else if (view.getId() == R.id.discover_category_unicef) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//        int childCount = ((LinearLayout) discover_category_unicef.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_unicef.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
//                childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//        discover_category_unicef.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//    }
//    else if (view.getId() == R.id.discover_category_child_education) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//        int childCount = ((LinearLayout) discover_category_child_education.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_child_education.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
//                childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//        discover_category_child_education.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//    }
//
//    else if (view.getId() == R.id.discover_category_others) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//        int childCount = ((LinearLayout) discover_category_others.getParent()).getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = ((LinearLayout) discover_category_others.getParent()).getChildAt(i);
//            if (childView instanceof TextView) {
////                childView.setBackgroundColor(0);
//                childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//            }
//        }
//        discover_category_others.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//    }
//}

//    private void handleMediaElementClick(View view) {
//        Boolean selected = false;
//        // Handle the click event for each element
//        if (view.getId() == R.id.discover_print_media) {
////        Toast.makeText(getApplicationContext(), "Button 1 Clicked", Toast.LENGTH_SHORT).show();
//            int childCount = ((LinearLayout) discover_print_media.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_print_media.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_print_media.setBackgroundResource(R.drawable.rounded_background_clicked);
//
////        discover_tonality_all.setBackgroundResource(R.color.brand_color);
//        } else if (view.getId() == R.id.discover_tv) {
//            int childCount = ((LinearLayout) discover_tv.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_tv.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_tv.setBackgroundResource(R.drawable.rounded_background_clicked);
////        discover_tonality_positive.setBackgroundResource(R.color.brand_color);
////        discover_tonality_positive.setText("Clicked");
//        } else if (view.getId() == R.id.discover_radio) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//            int childCount = ((LinearLayout) discover_radio.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_radio.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_radio.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//        }
//        // Add more cases as needed
//        else if (view.getId() == R.id.discover_online_media) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//            int childCount = ((LinearLayout) discover_online_media.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_online_media.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_online_media.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//        }
//
//        else if (view.getId() == R.id.discover_media_type_all) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//            int childCount = ((LinearLayout) discover_media_type_all.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_media_type_all.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_media_type_all.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//        }
//
//    }

//    private void handleTonalityElementClick(View view) {
//        Boolean selected = false;
//        // Handle the click event for each element
//        if (view.getId() == R.id.discover_tonality_all) {
////        Toast.makeText(getApplicationContext(), "Button 1 Clicked", Toast.LENGTH_SHORT).show();
//            int childCount = ((LinearLayout) discover_tonality_all.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_tonality_all.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_tonality_all.setBackgroundResource(R.drawable.rounded_background_clicked);
//
////        discover_tonality_all.setBackgroundResource(R.color.brand_color);
//        } else if (view.getId() == R.id.discover_tonality_positive) {
//            int childCount = ((LinearLayout) discover_tonality_positive.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_tonality_positive.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_tonality_positive.setBackgroundResource(R.drawable.rounded_background_clicked);
////        discover_tonality_positive.setBackgroundResource(R.color.brand_color);
////        discover_tonality_positive.setText("Clicked");
//        } else if (view.getId() == R.id.discover_tonality_negative) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//            int childCount = ((LinearLayout) discover_tonality_negative.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_tonality_negative.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_tonality_negative.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//        }
//        // Add more cases as needed
//        else if (view.getId() == R.id.discover_tonality_neutral) {
////            tonalityLinearLayout.setBackgroundResource(R.color.white);
////            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
//            int childCount = ((LinearLayout) discover_tonality_neutral.getParent()).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childView = ((LinearLayout) discover_tonality_neutral.getParent()).getChildAt(i);
//                if (childView instanceof TextView) {
//                    childView.setBackgroundResource(R.drawable.rounded_background); // Reset background color to transparent
//                    ((TextView) childView).setTextColor(ContextCompat.getColor(Discover.this, R.color.discover_items));
//                }
//            }
//            discover_tonality_neutral.setBackgroundResource(R.drawable.rounded_background_clicked);
//
//        }
//
//    }

//    Drop down element
private void showDropdown(View anchorView) {
    View popupView = LayoutInflater.from(this).inflate(R.layout.popup_dropdown, null);
    PopupWindow popupWindow = new PopupWindow(
            popupView,
            300, // Width of the popup
            400  // Height of the popup
    );

    // Set focusable to enable click events
    popupWindow.setFocusable(true);

    // Show the dropdown at the bottom of the button
    popupWindow.showAsDropDown(anchorView, 0, 0, Gravity.BOTTOM);

    // Set up click listeners for items in the custom drop-down
    setupDropdownItemClicks(popupView, popupWindow);
}

    private void setupDropdownItemClicks(View popupView, final PopupWindow popupWindow) {
        // Replace with your actual item views
        TextView unicef_online = popupView.findViewById(R.id.unicef_online);
        TextView child_protection = popupView.findViewById(R.id.child_protection);
        TextView child_health = popupView.findViewById(R.id.child_health);
        TextView unicef = popupView.findViewById(R.id.unicef);
        TextView child_education = popupView.findViewById(R.id.child_education);
        TextView others = popupView.findViewById(R.id.others);

        unicef_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item 1 click
                popupWindow.dismiss();
            }
        });

        child_protection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item 1 click
                popupWindow.dismiss();
            }
        });

        child_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item 1 click
                popupWindow.dismiss();
            }
        });

        unicef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item 1 click
                popupWindow.dismiss();
            }
        });

        child_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item 1 click
                popupWindow.dismiss();
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item 1 click
                popupWindow.dismiss();
            }
        });
    }


}

























//    Download File in Java
//public static void DownloadWebPage(String webpage)
//{
//    try {
//        // Create URL object
//        URL url = new URL(webpage);
//        BufferedReader readr =
//                new BufferedReader(new InputStreamReader(url.openStream()));
//
//        // Enter filename in which you want to download
//        BufferedWriter writer =
//                new BufferedWriter(new FileWriter("Download.html"));
//
//        // read each line from stream till end
//        String line;
//        while ((line = readr.readLine()) != null) {
//            writer.write(line);
//        }
//
//        readr.close();
//        writer.close();
//        System.out.println("Successfully Downloaded.");
//    }
//
//    // Exceptions
//    catch (MalformedURLException mue) {
//        System.out.println("Malformed URL Exception raised");
//    }
//    catch (IOException ie) {
//        System.out.println("IOException raised");
//    }
//}
//


















































//    private class DownloadHtmlAndConvertToPdfTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//            try {
//                // Download HTML content using Jsoup
//                String htmlUrl = urls[0];
//                Document document = Jsoup.connect(htmlUrl).get();
//
//                // Convert HTML to PDF
//                return convertHtmlToPdf(document);
//            } catch (IOException e) {
//                Log.e(TAG, "Error downloading or converting HTML to PDF", e);
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String pdfFilePath) {
//            super.onPostExecute(pdfFilePath);
//
//            if (pdfFilePath != null) {
//                // PDF conversion successful, you can now use the PDF file
//                Log.d(TAG, "PDF file created: " + pdfFilePath);
//            } else {
//                Log.e(TAG, "Error creating PDF file");
//            }
//        }
//    }
//
//
//        private String convertHtmlToPdf(Document document) {
//            // Create a WebView to render the HTML content
//            WebView webView = new WebView(Discover.this);
//            webView.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                    // Handle URL loading in the WebView
//                    return false;
//                }
//
//                @Override
//                public void onPageFinished(WebView view, String url) {
//                    // When the page finishes loading, create a PDF document
//                    createPdfDocument(view);
//                }
//            });
//
//            // Load the HTML content into the WebView
//            String htmlContent = document.html();
//            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
//
//            return null;
//        }
//
//        private void createPdfDocument(final WebView webView) {
//            // Create a PDF document
//            PdfDocument pdfDocument = new PdfDocument();
//            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(webView.getWidth(), webView.getHeight(), 1).create();
//            PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//            // Draw the WebView content onto the PDF page
//            Canvas canvas = page.getCanvas();
//            webView.draw(canvas);
//
//            pdfDocument.finishPage(page);
//
//            // Save the PDF document to a file
//            savePdfDocument(pdfDocument);
//
//            // Close the PDF document
//            pdfDocument.close();
//        }
//
//        private void savePdfDocument(final PdfDocument pdfDocument) {
//            // Save the PDF document to a file
//            String fileName = "output.pdf";
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
//
//            try (FileOutputStream outputStream = new FileOutputStream(file)) {
//                pdfDocument.writeTo(outputStream);
//
//                // Notify the system that the file has been created
//                MediaScannerConnection.scanFile(Discover.this,
//                        new String[]{file.getAbsolutePath()},
//                        null,
//                        null);
//
//            } catch (IOException e) {
//                Log.e(TAG, "Error saving PDF document", e);
//            }






