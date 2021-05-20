package com.example.and1y294418.dataDeprecated;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {BookLocal.class}, version = 1)
public abstract class LocalBookDatabase extends RoomDatabase
{
    private static LocalBookDatabase instance;
    public abstract BookStorageDAO bookStorageDAO();

    public static synchronized LocalBookDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LocalBookDatabase.class,
                    "local_bookDB").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
