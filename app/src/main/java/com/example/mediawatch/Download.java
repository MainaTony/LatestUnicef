package com.example.mediawatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Download extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
//        TextView downloadItem = findViewById(R.id.downloadItem);
        RecyclerView downloadRecycler = findViewById(R.id.downloadRecycler);



        List<File> downloadedFiles = getDownloadedFiles();

//        for (File file : downloadedFiles) {
////            downloadedFiles.add(file);
//            // Do something with each downloaded file
//            // For example, print the file name
////            System.out.println(file.getName());
//            downloadItem.setText(file.getName());
//        }
        // Create a list of items

        // Add more items as needed

        // Create RecyclerView and set its adapter
        ItemAdapter adapter = new ItemAdapter(downloadedFiles);
        downloadRecycler.setLayoutManager(new LinearLayoutManager(this));
        downloadRecycler.setAdapter(adapter);
    }
    public static List<File> getDownloadedFiles() {
        List<File> downloadedFiles = new ArrayList<>();

        // Get the Downloads directory
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        // Check if the directory exists and is a directory
        if (downloadsDir.exists() && downloadsDir.isDirectory()) {
            // List all files in the directory
            File[] files = downloadsDir.listFiles();

            // Add each file to the list
            if (files != null) {
                for (File file : files) {
                    downloadedFiles.add(file);
                }
            }
        }

        return downloadedFiles;
    }


}