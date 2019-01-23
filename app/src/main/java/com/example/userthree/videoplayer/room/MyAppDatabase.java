package com.example.userthree.videoplayer.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.userthree.videoplayer.model.VideosList;

@Database(entities = {VideosList.class},version = 1,exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase
{
    public abstract MyDao myDao();
}
