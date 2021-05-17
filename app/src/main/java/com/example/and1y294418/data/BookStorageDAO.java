package com.example.and1y294418.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.and1y294418.data.BookLocal;

import java.util.List;

@Dao
public interface BookStorageDAO
{
    @Insert
    void insert(BookLocal book);

    @Query("Select title From local_books")
    BookLocal getBookByTitle(String title);

    @Query("Select * From local_books")
    List<BookLocal> getAllLocalBooks();
}
