package com.example.userthree.videoplayer.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.userthree.videoplayer.model.Data;
import com.example.userthree.videoplayer.model.RespVideos;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

   Data dataitem;
   List<RespVideos> respVideos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataitem=new Data();
        respVideos=new ArrayList<>();
    }


    public void setDataitem(List<RespVideos> respVideos) {
        this.respVideos=respVideos;
    }

    public List<RespVideos> getDataitem()
    {
        return respVideos;
    }
}
