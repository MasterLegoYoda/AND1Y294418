package com.example.and1y294418.data;

import java.util.ArrayList;
import java.util.Arrays;

public class BookListMessage
{
    private String[] title;

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }
    public ArrayList<String> getBookTitlesInArrayList()
    {
        return new ArrayList<String>(Arrays.asList(title));
    }
}
