package com.example.userthree.videoplayer.model;

import com.example.userthree.videoplayer.contract.MainActivityContract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable,MainActivityContract.Model {

    ArrayList<RespVideos> datalist;

    public ArrayList<RespVideos> getDatalist() {
        return datalist;
    }

    public void setDatalist(ArrayList<RespVideos> datalist) {
        this.datalist = datalist;
    }


    @Override
    public ArrayList<RespVideos> getData() {
        return  datalist;
    }
}
