package com.example.userthree.videoplayer.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.userthree.videoplayer.R;
import com.example.userthree.videoplayer.adapter.RelatedVideosAdapter;
import com.example.userthree.videoplayer.constants.IntentConstants;
import com.example.userthree.videoplayer.contract.VideoDisplayActivityContract;
import com.example.userthree.videoplayer.interfaces.ItemSelectListener;
import com.example.userthree.videoplayer.interfaces.ItemSelector;
import com.example.userthree.videoplayer.model.RespVideos;
import com.example.userthree.videoplayer.presenter.VideoDisplayActivityPresenter;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoDisplayActivity extends AppCompatActivity implements VideoDisplayActivityContract.View{

    @BindView(R.id.video_view)
    PlayerView playerView;

    @BindView(R.id.tvPlayingVideoTitle)
    TextView tvPlayingVideoTitle;

    @BindView(R.id.tvPlayingvideoDescription)
    TextView tvtPlayingvideodescription;

    @BindView(R.id.rvRelatedvideosList)
    RecyclerView rvRelatedVideosList;

    LinearLayoutManager linearLayoutManager;
    RelatedVideosAdapter relatedVideosAdapter;

    private VideoDisplayActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_display);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mPresenter=new VideoDisplayActivityPresenter(this,intent,VideoDisplayActivity.this);
    }

    //for starting the the video player
    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start(this);

    }

    //to stop the video player
    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.saveTimer();
        playerView.setPlayer(null);

    }


    //for playing selected video
    @Override
    public void playSelectedVideo(SimpleExoPlayer player,String title, String description) {
        playerView.setPlayer(player);
        tvPlayingVideoTitle.setText(title);
        if (description.length()>100)
            description=description.substring(0,100);
        tvtPlayingvideodescription.setText(description);
    }


    //for displaying the list of related videos without including the current playing video
    @Override
    public void displayRelatedVideosList(ArrayList<RespVideos> list) {

        linearLayoutManager = new LinearLayoutManager(VideoDisplayActivity.this, LinearLayoutManager.VERTICAL, false);
        rvRelatedVideosList.setLayoutManager(linearLayoutManager);
        relatedVideosAdapter = new RelatedVideosAdapter(VideoDisplayActivity.this, list, new ItemSelector() {
            @Override
            public void onSelect(String position) {

                mPresenter.itemSelected(VideoDisplayActivity.this,position);

            }
        });
        rvRelatedVideosList.setAdapter(relatedVideosAdapter);

    }


    //for updating the list items inside recycleview
    //will update related videos list
    @Override
    public void updateVideolist(ArrayList<RespVideos> list, int index) {
        //will notify adapter
        relatedVideosAdapter.changeSelection(list, index);
    }
}
