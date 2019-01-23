package com.example.userthree.videoplayer.contract;

import com.example.userthree.videoplayer.model.RespVideos;

import java.util.ArrayList;

public interface MainActivityContract
{
    interface View{
        void loadVideo(ArrayList<RespVideos> respVideos);
    }

    interface Model{
        ArrayList<RespVideos> getData();
    }

    interface Presenter{
        void fetchData();
    }
}
