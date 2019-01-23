package com.example.userthree.videoplayer.presenter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.userthree.videoplayer.constants.IntentConstants;
import com.example.userthree.videoplayer.contract.VideoDisplayActivityContract;
import com.example.userthree.videoplayer.model.RespVideos;
import com.example.userthree.videoplayer.model.VideosList;
import com.example.userthree.videoplayer.room.MyAppDatabase;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class VideoDisplayActivityPresenter implements VideoDisplayActivityContract.Presenter{

    private VideoDisplayActivityContract.View mView;
    SimpleExoPlayer player;
    ArrayList<RespVideos> list;
    ArrayList<RespVideos> reallist;
    int position;
    public static MyAppDatabase myAppDatabase;

    public VideoDisplayActivityPresenter(VideoDisplayActivityContract.View view, Intent intent,Context context)
    {
        mView=view;
        initPresenter(intent);
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"userdb").allowMainThreadQueries().build();

    }

    //for initializing value in presenter
    void initPresenter(Intent intent){
        list = (ArrayList<RespVideos>) intent.getSerializableExtra("LIST");
        reallist = new ArrayList<>();
        reallist.addAll(list);
        position = intent.getExtras().getInt(IntentConstants.POSITION);
        //for displaying list of related videos
        showListOfVideos();
    }

    @Override
    public void start(Context context) {
        player = ExoPlayerFactory.newSimpleInstance(context, new DefaultTrackSelector());
        //sending the position and time of the video to be played
        playVideo(context,reallist,0);
    }

    //called to save the length of video played for respective video
    @Override
    public void saveTimer() {
        int pos=player.getCurrentWindowIndex();
        long tym;
        //if the length of video played is equal to the total lenght then save the timer to 0
        if (player.getCurrentPosition()==player.getDuration())
        {
            tym=0;
        }
        else {
            tym=player.getCurrentPosition();
        }

        int count=0;

        VideosList videosList=new VideosList(pos,tym);
        List<VideosList> arrayList=myAppDatabase.myDao().getTimer();
        //if the timer for a particular video is present in the list then update it else add the video position and its timer to the list
        if (arrayList.size()>0)
        {
            for (int i=0;i<arrayList.size();i++)
            {
                if (arrayList.get(i).getPos()==pos)
                {
                    myAppDatabase.myDao().updateTimer(videosList);
                    count++;
                }
            }
        }

        if (count==0)
        myAppDatabase.myDao().addTimer(videosList);
    }

    //this is to check if a particular video with its timer is present or not
    //if present then return the timer else return video start time as 0
    public long checkForTimer(int p)
    {
        long t=0;
        List<VideosList> arrayList=myAppDatabase.myDao().getTimer();
        if (arrayList.size()>0)
        {
            for (int i=0;i<arrayList.size();i++)
            {
                if (arrayList.get(i).getPos()==p)
                {
                    t=arrayList.get(i).getTime();
                }
            }
        }
       return t;
    }


    //for getting media source from the video url
    private ExtractorMediaSource buildMediaSource(Context context,Uri uri) {

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "Exoplayer"));
        ExtractorMediaSource mediaSource1 = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
        return mediaSource1;
    }

    //for concatenating all the video list into one playlist and the play the video
    private void playVideo(Context context,ArrayList<RespVideos> list, long time){
        ExtractorMediaSource[] mediaSources = new ExtractorMediaSource[list.size()];
        for (int i = 0; i < list.size(); i++) {
            mediaSources[i] = buildMediaSource(context,Uri.parse(list.get(i).getUrl()));
        }

        ConcatenatingMediaSource concatenatingMediaSource = new ConcatenatingMediaSource(mediaSources);

        player.prepare(concatenatingMediaSource);
        long t= checkForTimer(position);
        player.seekTo(position, t);
        player.setPlayWhenReady(false);
        mView.playSelectedVideo(player,list.get(position).getTitle(),list.get(position).getDescription());
    }

    //for releasing the videoplayer after the work is done
    @Override
    public void stop() {
        player.release();
        player = null;

    }

    //itemselectlistener for related videos list
    @Override
    public void itemSelected(Context context,String pos) {
        int indx = 0;
        for (int i = 0; i < reallist.size(); i++) {
            if (reallist.get(i).getId().equals(pos)) {
                indx = i;
                list.clear();
                list.addAll(reallist);
                //list.remove(i);
            }
        }
        saveTimer();
        position = indx;
        playVideo(context,list,0);

        mView.updateVideolist(list,indx);
    }

    //for removing the current video from video list and displaying the related videos list
        public void showListOfVideos() {
        list.remove(position);
        mView.displayRelatedVideosList(list);
    }
}
