package com.example.userthree.videoplayer.retrofit;

import com.example.userthree.videoplayer.model.RespVideos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService
{
    @GET("media.json?print=pretty")
    Call<ArrayList<RespVideos>> getData();
}
