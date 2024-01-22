package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.mediawatch.databinding.ActivityMainBinding;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.io.File;

public class NewsActivity extends AppCompatActivity {
//    ActivityMainBinding binding;
    TextView news_activity_title, storyUrl;
    ScrollView newsActivityVerticalScrollView;

    TextView news_activity_category;
    TextView news_activity_summary;
    Button downloadStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_news);
        String title = getIntent().getStringExtra("title");
        String category = getIntent().getStringExtra("category");
        String summary = getIntent().getStringExtra("summary");
        String storyurl = getIntent().getStringExtra("storyurl");

        newsActivityVerticalScrollView = findViewById(R.id.newsActivityVerticalScrollView);


        news_activity_title = findViewById(R.id.news_activity_title);
        news_activity_category = findViewById(R.id.news_activity_category);
        news_activity_summary = findViewById(R.id.news_activity_summary);
        downloadStory = findViewById(R.id.downloadStory);
        storyUrl = findViewById(R.id.storyUrl);


        news_activity_title.setText(title);
        news_activity_category.setText(category);
        news_activity_summary.setText(summary);
        storyUrl.setText(storyurl);

        if(storyurl.isEmpty()){
            storyUrl.setText("story url not available");
        }

        storyUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!storyurl.isEmpty()){
                    String url = storyurl;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                else {
//                    storyUrl.setText("story url not available");
                    Toast.makeText(NewsActivity.this, "Story url not available", Toast.LENGTH_SHORT).show();
                }

            }
        });

        downloadStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textToSave = summary;
                String pdfFileName = title+".pdf";
                createPdf(textToSave, pdfFileName);

                Toast.makeText(NewsActivity.this, "File Successfully downloaded", Toast.LENGTH_SHORT).show();

            }
        });


//        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.home:
//                    // Handle item 1 click
//                    startActivity(new Intent(NewsActivity.this, MainActivity.class));
//                    break;
//                case R.id.news:
//                    // Handle item 2 click
//
//                    break;
//                // Add more cases for additional menu items
//                case R.id.chat:
////                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
//                    // Handle item 3 click
//                    break;
//                case R.id.download:
////                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
//                    // Handle item 4 click
//                    break;
//            }
//            return false;
//        });
    }

    public static void createPdf(String text, String pdfFileName) {
        // Create a PdfWriter object
        try {
            File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pdfFileName);
            PdfWriter writer = new PdfWriter(pdfFile);

            // Create a PdfDocument object
            PdfDocument pdfDocument = new PdfDocument(writer);

            // Create a Document object
            Document document = new Document(pdfDocument);

            // Add text to the document
            document.add(new Paragraph(text));

            // Close the document
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public void openWebsite(View view) {
//        // Replace "https://www.example.com" with your actual website URL
////        String url = storyurl;
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        startActivity(intent);
//    }




}

