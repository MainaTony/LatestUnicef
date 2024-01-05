package com.example.mediawatch;

import static com.example.mediawatch.MainActivity.getMediaWatchArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintDocumentAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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




public class Discover extends AppCompatActivity implements View.OnClickListener {
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
//        healthTxt = findViewById(R.id.healthTxt);

//        String fileName = "https://forexbee.co/chart-patterns-pdf/";
//        String content = "Hello, this is an example file content.";

//        saveFileToDownloads(fileName, content);

        // Sample HTML content
//        String htmlContent = "<html><body><h1>Hello, iTextPdf!</h1></body></html>";

        // Specify the output path for the PDF file
//        String outputPdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/output.pdf";

        // Convert HTML to PDF
//        convert(htmlContent, outputPdfPath);

        // Convert HTML to PDF
//        convertHtmlToPdf(htmlContent, outputPdfPath);

//        convertUsingHtmlConverter(htmlContent, outputPdfPath);



//        String url = "https://www.geeksforgeeks.org/";
//            DownloadWebPage(url);

        // Sample HTML content

//
//        // Specify the output path for the PDF file
//        String outputPdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/output.pdf";
//
//        // Convert HTML to PDF
//        convert(htmlContent, outputPdfPath);

//        String outputPdfPath = getFilesDir().getAbsolutePath() + File.separator + "mine.pdf";
//        String htmlContent = "<html><body><h1>Hello, World!</h1></body></html>";
//        String htmlContent = "<html><body><h1>Hello, World!</h1></body></html>";
//        String outputPdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/rivieler.pdf";
//        convertUsingHtmlConverter(htmlContent, outputPdfPath);


//        Media Type Section binding
        discover_print_media = findViewById(R.id.discover_print_media);
        discover_tv = findViewById(R.id.discover_tv);
        discover_radio = findViewById(R.id.discover_radio);
        discover_online_media = findViewById(R.id.discover_online_media);
        discover_media_type_all = findViewById(R.id.discover_media_type_all);
        tonalityLinearLayout = findViewById(R.id.tonalityLinearLayout);
        thematicLinearLayout = findViewById(R.id.thematicLinearLayout);


//        Horizontal Scroll views
        tonalityHorizontalScrollView = findViewById(R.id.tonalityHorizontalScrollView);


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

//        discover_category_unicef_online_media.setOnClickListener(this);
//        discover_category_governance.setOnClickListener(this);
//        discover_category_child_protection.setOnClickListener(this);
//        discover_category_child_health.setOnClickListener(this);
//        discover_category_unicef.setOnClickListener(this);
//        discover_category_child_education.setOnClickListener(this);
//        discover_category_others.setOnClickListener(this);

//        for (int i = 0; i < tonalityLinearLayout.getChildCount(); i++) {
//            View child = tonalityLinearLayout.getChildAt(i);
//            child.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), "Button 2 Clicked", Toast.LENGTH_SHORT).show();
//                    handleElementClick(v);
//                }
//            });
//        }
//        for (int i = 0; i < tonalityLinearLayout.getChildCount(); i++) {
//            View child = tonalityLinearLayout.getChildAt(i);
//            child.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }


        discover_category_unicef_online_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                thematicLinearLayout.setBackgroundResource(R.color.black);
//                thematicLinearLayout.setBackgroundResource(R.color.black);
                MediaWatch[] onlineMediaWatch = unicefOnlineMedia.toArray(unicefOnlineMedia.toArray(new MediaWatch[0]));
                UnicefAdapter onlineMediaWatchAdapter = new UnicefAdapter(onlineMediaWatch);
                handleElementClick(v);
                discover_feed_recycler.setAdapter(onlineMediaWatchAdapter);


            }
        });
        discover_category_governance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                thematicLinearLayout.setBackgroundResource(R.color.black);
                MediaWatch[] governanceMediaWatch = governance.toArray(governance.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(governanceMediaWatch);
                handleElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_category_child_protection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thematicLinearLayout.setBackgroundResource(R.color.transparent);
                MediaWatch[] childProtectionMediaWatch = childProtection.toArray(childProtection.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(childProtectionMediaWatch);
                handleElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_category_child_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaWatch[] childHealthMediaWatch = childHealth.toArray(childHealth.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(childHealthMediaWatch);
                handleElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_category_unicef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaWatch[] unicefMediaWatch = unicef.toArray(unicef.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(unicefMediaWatch);
                handleElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_category_child_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                discover_category_child_education.setBackgroundResource(R.color.brand_color);
//                discover_category_child_education.setTextColor(Color.parseColor("#FFFFFF"));
                MediaWatch[] childMediaWatch = childEducation.toArray(childEducation.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(childMediaWatch);
                handleElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });

        discover_category_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                discover_category_others.setBackgroundResource(R.color.brand_color);
//                discover_category_others.setTextColor(Color.parseColor("#FFFFFF"));
                MediaWatch[] othersMediaWatch = others.toArray(others.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(othersMediaWatch);
                handleElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });


//        Media Type On Click Listeners
        discover_print_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Print Media = "+printMedia.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] printMediaWatch = printMedia.toArray(printMedia.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(printMediaWatch);
                handleMediaElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "TV = "+tv.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] tvMediaWatch = tv.toArray(tv.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(tvMediaWatch);
                handleMediaElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Radio = "+radio.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] radioMediaWatch = radio.toArray(radio.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(radioMediaWatch);
                handleMediaElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_online_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Online Media = "+onlineMedia.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] onlineMediaWatch = onlineMedia.toArray(onlineMedia.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(onlineMediaWatch);
                handleMediaElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });
        discover_media_type_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
                handleMediaElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });

//        Click Listeners
        discover_tonality_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MediaWatch[] positiveMediaWatch = positive.toArray(positive.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(storedMediaWatchArray);
                handleTonalityElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);

            }
        });
        discover_tonality_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Positive = "+positive.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] positiveMediaWatch = positive.toArray(positive.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
                handleTonalityElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });

        discover_tonality_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Negative = "+negative.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] positiveMediaWatch = negative.toArray(negative.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
                handleTonalityElementClick(v);
                discover_feed_recycler.setAdapter(unicefAdapter);
            }
        });

        discover_tonality_neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Discover.this, "Neutral = "+neutral.size(), Toast.LENGTH_SHORT).show();
                MediaWatch[] positiveMediaWatch = neutral.toArray(neutral.toArray(new MediaWatch[0]));
                UnicefAdapter unicefAdapter = new UnicefAdapter(positiveMediaWatch);
                handleTonalityElementClick(v);
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

    public static void displayResult() {
        System.out.print("Final result");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discover_category_unicef_online_media:
//                Toast.makeText(Discover.this, "TV = "+positive.size(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "Unicef Online Media Clicked", Toast.LENGTH_SHORT).show();
                discover_category_child_education.setBackgroundResource(R.color.brand_color);
                break;
            default:
                break;
        }
    }
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
private void handleElementClick(View view) {
        Boolean selected = false;
    // Handle the click event for each element
    if (view.getId() == R.id.discover_category_governance) {
//        Toast.makeText(getApplicationContext(), "Button 1 Clicked", Toast.LENGTH_SHORT).show();
        int childCount = ((LinearLayout) discover_category_governance.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_governance.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
            discover_category_governance.setBackgroundResource(R.color.brand_color);

//        discover_tonality_all.setBackgroundResource(R.color.brand_color);
    } else if (view.getId() == R.id.discover_category_unicef_online_media) {
        int childCount = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
        discover_category_unicef_online_media.setBackgroundResource(R.color.brand_color);
//        discover_tonality_positive.setBackgroundResource(R.color.brand_color);
//        discover_tonality_positive.setText("Clicked");
    } else if (view.getId() == R.id.discover_category_unicef_online_media) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
        int childCount = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_unicef_online_media.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
        discover_category_unicef_online_media.setBackgroundResource(R.color.brand_color);

    }
    // Add more cases as needed
    else if (view.getId() == R.id.discover_category_child_protection) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
        int childCount = ((LinearLayout) discover_category_child_protection.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_child_protection.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
        discover_category_child_protection.setBackgroundResource(R.color.brand_color);

    }

    else if (view.getId() == R.id.discover_category_child_health) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
        int childCount = ((LinearLayout) discover_category_child_health.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_child_health.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
        discover_category_child_health.setBackgroundResource(R.color.brand_color);

    }
    else if (view.getId() == R.id.discover_category_unicef) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
        int childCount = ((LinearLayout) discover_category_unicef.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_unicef.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
        discover_category_unicef.setBackgroundResource(R.color.brand_color);

    }
    else if (view.getId() == R.id.discover_category_child_education) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
        int childCount = ((LinearLayout) discover_category_child_education.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_child_education.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
        discover_category_child_education.setBackgroundResource(R.color.brand_color);

    }

    else if (view.getId() == R.id.discover_category_others) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
        int childCount = ((LinearLayout) discover_category_others.getParent()).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = ((LinearLayout) discover_category_others.getParent()).getChildAt(i);
            if (childView instanceof TextView) {
                childView.setBackgroundColor(0); // Reset background color to transparent
            }
        }
        discover_category_others.setBackgroundResource(R.color.brand_color);

    }
}

    private void handleMediaElementClick(View view) {
        Boolean selected = false;
        // Handle the click event for each element
        if (view.getId() == R.id.discover_print_media) {
//        Toast.makeText(getApplicationContext(), "Button 1 Clicked", Toast.LENGTH_SHORT).show();
            int childCount = ((LinearLayout) discover_print_media.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_print_media.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_print_media.setBackgroundResource(R.color.brand_color);

//        discover_tonality_all.setBackgroundResource(R.color.brand_color);
        } else if (view.getId() == R.id.discover_tv) {
            int childCount = ((LinearLayout) discover_tv.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_tv.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_tv.setBackgroundResource(R.color.brand_color);
//        discover_tonality_positive.setBackgroundResource(R.color.brand_color);
//        discover_tonality_positive.setText("Clicked");
        } else if (view.getId() == R.id.discover_radio) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
            int childCount = ((LinearLayout) discover_radio.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_radio.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_radio.setBackgroundResource(R.color.brand_color);

        }
        // Add more cases as needed
        else if (view.getId() == R.id.discover_online_media) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
            int childCount = ((LinearLayout) discover_online_media.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_online_media.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_online_media.setBackgroundResource(R.color.brand_color);

        }

        else if (view.getId() == R.id.discover_media_type_all) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
            int childCount = ((LinearLayout) discover_media_type_all.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_media_type_all.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_media_type_all.setBackgroundResource(R.color.brand_color);

        }

    }

    private void handleTonalityElementClick(View view) {
        Boolean selected = false;
        // Handle the click event for each element
        if (view.getId() == R.id.discover_tonality_all) {
//        Toast.makeText(getApplicationContext(), "Button 1 Clicked", Toast.LENGTH_SHORT).show();
            int childCount = ((LinearLayout) discover_tonality_all.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_tonality_all.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_tonality_all.setBackgroundResource(R.color.brand_color);

//        discover_tonality_all.setBackgroundResource(R.color.brand_color);
        } else if (view.getId() == R.id.discover_tonality_positive) {
            int childCount = ((LinearLayout) discover_tonality_positive.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_tonality_positive.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_tonality_positive.setBackgroundResource(R.color.brand_color);
//        discover_tonality_positive.setBackgroundResource(R.color.brand_color);
//        discover_tonality_positive.setText("Clicked");
        } else if (view.getId() == R.id.discover_tonality_negative) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
            int childCount = ((LinearLayout) discover_tonality_negative.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_tonality_negative.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_tonality_negative.setBackgroundResource(R.color.brand_color);

        }
        // Add more cases as needed
        else if (view.getId() == R.id.discover_tonality_neutral) {
//            tonalityLinearLayout.setBackgroundResource(R.color.white);
//            thematicLinearLayout.setBackgroundResource(R.color.brand_color);
            int childCount = ((LinearLayout) discover_tonality_neutral.getParent()).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = ((LinearLayout) discover_tonality_neutral.getParent()).getChildAt(i);
                if (childView instanceof TextView) {
                    childView.setBackgroundColor(0); // Reset background color to transparent
                }
            }
            discover_tonality_neutral.setBackgroundResource(R.color.brand_color);

        }

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






