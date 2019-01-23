package com.example.userthree.videoplayer.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.userthree.videoplayer.model.VideosList;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addTimer(VideosList videosList);

    @Query("select * from videoslist")
    public List<VideosList> getTimer();

    @Delete
    public void deleteTimer(VideosList videosList);

    @Update
    public void updateTimer(VideosList videosList);
}
