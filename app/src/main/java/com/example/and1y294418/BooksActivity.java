package com.example.and1y294418;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BooksActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    BookListAdapter booksListAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        databaseReference = FirebaseDatabase.getInstance().getReference("books");
        databaseReference.keepSynced(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BookItem> options
                = new FirebaseRecyclerOptions.Builder<BookItem>()
                .setQuery(databaseReference, BookItem.class)
                .build();

        booksListAdapter = new BookListAdapter(options);
        recyclerView.setAdapter(booksListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        booksListAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        booksListAdapter.stopListening();
    }
}