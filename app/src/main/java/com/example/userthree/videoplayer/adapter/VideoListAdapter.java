package com.example.userthree.videoplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.userthree.videoplayer.R;
import com.example.userthree.videoplayer.interfaces.ItemSelectListener;
import com.example.userthree.videoplayer.model.RespVideos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder>{

    Context context;
    private String[] text=new String[0];
    private ArrayList<RespVideos> list;
    private LayoutInflater mInflater;
    ItemSelectListener itemSelectListener;

    public VideoListAdapter(Context context,ArrayList<RespVideos> list,ItemSelectListener itemSelectListener)
    {
        this.context=context;
        this.list=list;
        this.mInflater=LayoutInflater.from(context);
        this.itemSelectListener=itemSelectListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.adapter_videolist,viewGroup,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Picasso.get().load(list.get(position).getThumb()).into(holder.imgThumbnail);
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDescription.setText(list.get(position).getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectListener.onSelect(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgThumbnail)
        public ImageView imgThumbnail;

        @BindView(R.id.tvTitle)
        public TextView tvTitle;

        @BindView(R.id.tvDescription)
        public TextView tvDescription;

        @BindView(R.id.card)
        public CardView cardView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
