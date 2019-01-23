package com.example.userthree.videoplayer.presenter;

import com.example.userthree.videoplayer.adapter.VideoListAdapter;
import com.example.userthree.videoplayer.contract.MainActivityContract;
import com.example.userthree.videoplayer.interfaces.ItemSelectListener;
import com.example.userthree.videoplayer.model.RespVideos;
import com.example.userthree.videoplayer.retrofit.ApiUtils;
import com.example.userthree.videoplayer.retrofit.UserService;
import com.example.userthree.videoplayer.routemanager.RouteManager;
import com.example.userthree.videoplayer.view.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityContract.Presenter {


    private MainActivityContract.View mView;
    private MainActivityContract.Model mModel;
    UserService userService;

    //constructor for initialising user services and view
    public MainActivityPresenter(MainActivityContract.View view) {
        mView = view;
        userService = ApiUtils.getUserService();
        initPresenter();

    }

    private void initPresenter() {
        fetchData();
    }

    //fetching data from the api
    @Override
    public void fetchData() {

        Call<ArrayList<RespVideos>> call = userService.getData();
        call.enqueue(new Callback<ArrayList<RespVideos>>() {
            @Override
            public void onResponse(Call<ArrayList<RespVideos>> call, Response<ArrayList<RespVideos>> response) {
                final ArrayList<RespVideos> respVideos = response.body();

                //sending data to recycleview adapter
                mView.loadVideo(respVideos);
            }

            @Override
            public void onFailure(Call<ArrayList<RespVideos>> call, Throwable t) {

            }
        });

    }
}
