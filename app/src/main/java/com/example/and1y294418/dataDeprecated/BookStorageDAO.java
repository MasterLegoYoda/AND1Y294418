package com.example.and1y294418.dataDeprecated;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookStorageDAO
{
    @Insert
    void insert(BookLocal book);

    @Update
    void Update(BookLocal bookLocal);

    @Query("Select * From local_books where title LIKE :title")
    BookLocal getBookByTitle(String title);

    @Query("Select * From local_books")
    LiveData<List<BookLocal>> getAllLocalBooks();
}
