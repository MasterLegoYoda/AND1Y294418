package com.example.and1y294418;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        try {
            FileInputStream fileIn=openFileInput("sample.pdf");
            pdfView.fromStream(fileIn).enableSwipe(true).swipeHorizontal(true).pageSnap(true).load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //le comment
    }


}