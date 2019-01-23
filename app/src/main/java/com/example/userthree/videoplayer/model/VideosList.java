package com.example.userthree.videoplayer.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "videoslist")
public class VideosList
{
    public VideosList(int pos, long time) {
        this.pos = pos;
        this.time = time;
    }

    @PrimaryKey

    int pos;

    @ColumnInfo(name = "time")
    long time;


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
