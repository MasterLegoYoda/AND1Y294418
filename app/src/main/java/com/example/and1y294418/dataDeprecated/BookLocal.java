package com.example.and1y294418.dataDeprecated;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "local_books")
public class BookLocal
{
    @PrimaryKey(autoGenerate = true)
    private int page;
    private String filename;
    private String title;

    public BookLocal(int page, String filename, String title) {
        this.page = page;
        this.filename = filename;
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
