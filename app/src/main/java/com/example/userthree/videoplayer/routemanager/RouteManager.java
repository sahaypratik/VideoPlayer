package com.example.userthree.videoplayer.routemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.userthree.videoplayer.constants.IntentConstants;
import com.example.userthree.videoplayer.model.RespVideos;
import com.example.userthree.videoplayer.view.MainActivity;
import com.example.userthree.videoplayer.view.VideoDisplayActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RouteManager
{
    public static void redirectToVideoActivity(Context context, int position, ArrayList<RespVideos> list)
    {
        Intent intent=new Intent(context, VideoDisplayActivity.class);
        Bundle bundle=new Bundle();
        intent.putExtra("LIST", (Serializable) list);
        intent.putExtra(IntentConstants.POSITION,position);
        context.startActivity(intent);
    }

    public static void redirectToMainActivity(Context context)
    {
        Intent intent=new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }


}
