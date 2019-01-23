package com.example.userthree.videoplayer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.userthree.videoplayer.R;
import com.example.userthree.videoplayer.adapter.VideoListAdapter;
import com.example.userthree.videoplayer.contract.MainActivityContract;
import com.example.userthree.videoplayer.interfaces.ItemSelectListener;
import com.example.userthree.videoplayer.model.Data;
import com.example.userthree.videoplayer.model.RespVideos;
import com.example.userthree.videoplayer.presenter.MainActivityPresenter;
import com.example.userthree.videoplayer.retrofit.ApiUtils;
import com.example.userthree.videoplayer.retrofit.UserService;
import com.example.userthree.videoplayer.routemanager.RouteManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.rvVideoList)
    RecyclerView rvVideoList;

    LinearLayoutManager linearLayoutManager;
    VideoListAdapter videoListAdapter;
    private MainActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //setting linear layout for recycleview
        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rvVideoList.setLayoutManager(linearLayoutManager);

        mPresenter = new MainActivityPresenter(this);

    }


    //setting adapter with the data received from api call
    @Override
    public void loadVideo(final ArrayList<RespVideos> respVideos) {
        videoListAdapter = new VideoListAdapter(MainActivity.this, respVideos, new ItemSelectListener() {
            @Override
            public void onSelect(int position) {
                //click event for item in the recycleview
                //this will navigate to next activity with the position of item click and list of data
                RouteManager.redirectToVideoActivity(MainActivity.this, position, respVideos);
            }
        });
        rvVideoList.setAdapter(videoListAdapter);

    }
}
