package com.example.userthree.videoplayer.contract;

import android.content.Context;

import com.example.userthree.videoplayer.model.RespVideos;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public interface VideoDisplayActivityContract {
    interface View {
        void playSelectedVideo(SimpleExoPlayer player,String title, String decscription);
        void updateVideolist(ArrayList<RespVideos> list, int index);
        void displayRelatedVideosList(ArrayList<RespVideos> list);
    }

    interface Model {

    }

    interface Presenter {
        void start(Context context);

        void saveTimer();

        void stop();

        void itemSelected(Context context,String position);
    }
}
