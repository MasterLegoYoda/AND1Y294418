package com.example.and1y294418;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ReaderActivity extends AppCompatActivity {
    String title;
    String filePath;
    String id;
    PDFView pdfView;
    DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    String userId;
    int defaultPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        pdfView = (PDFView) findViewById(R.id.pdfView);

        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        filePath = extras.getString("filePath");
        id = extras.getString("id");
        ((AppCompatActivity) this).getSupportActionBar().setTitle(title);

        mDatabase.child("userReading").child(userId).child("books").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    if (task.getResult().getValue() == null) {
                        mDatabase.child("userReading").child(userId).child("books").child(id).setValue(0);
                    } else {
                        defaultPage = (int) task.getResult().getValue(Integer.class);
                    }
                }
            }
        });
        new RetrivePDFfromUrl().execute(filePath);
    }


    // create an async task class for loading pdf file from URL.
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).onPageChange(new OnPageChangeListener() {
                @Override
                public void onPageChanged(int page, int pageCount) {
                    mDatabase.child("userReading").child(userId).child("books").child(id).setValue(page);
                }
            }).defaultPage(defaultPage).load();
        }
    }
}