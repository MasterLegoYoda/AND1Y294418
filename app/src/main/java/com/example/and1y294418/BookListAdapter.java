package com.example.and1y294418;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.io.Serializable;


public class BookListAdapter extends FirebaseRecyclerAdapter<BookItem, BookListAdapter.bookItemViewholder> {

    public BookListAdapter(@NonNull FirebaseRecyclerOptions<BookItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull bookItemViewholder holder, int position, @NonNull BookItem model) {
        holder.title.setText(model.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReaderActivity.class);
                intent.putExtra("title",  model.getTitle());
                intent.putExtra("filePath",  model.getFilePath());
                intent.putExtra("id",  model.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public bookItemViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new BookListAdapter.bookItemViewholder(view);
    }

    class bookItemViewholder extends RecyclerView.ViewHolder {
        TextView title;

        public bookItemViewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle);
        }
    }
}
