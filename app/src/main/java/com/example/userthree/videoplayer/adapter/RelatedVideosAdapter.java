package com.example.userthree.videoplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userthree.videoplayer.R;
import com.example.userthree.videoplayer.interfaces.ItemSelectListener;
import com.example.userthree.videoplayer.interfaces.ItemSelector;
import com.example.userthree.videoplayer.model.RespVideos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RelatedVideosAdapter extends RecyclerView.Adapter<RelatedVideosAdapter.ViewHolder>{


    Context context;
    //private String[] text=new String[0];
    private ArrayList<RespVideos> list;
    private LayoutInflater mInflater;
    ItemSelector itemSelector;
    ArrayList<RespVideos> sList;

    int r=0;

    public RelatedVideosAdapter(Context context, ArrayList<RespVideos> list, ItemSelector itemSelector)
    {
      this.context=context;
      this.list=list;
      this.mInflater=LayoutInflater.from(context);
      this.itemSelector=itemSelector;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.adapter_related_videos,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        if (r==0)
        {
            Picasso.get().load(list.get(position).getThumb()).into(holder.imgRelatedVideoThumb);
            holder.tvRelatedVideotitle.setText(list.get(position).getTitle());
            holder.tvRelatedvideoDescription.setText(list.get(position).getDescription());
            holder.ll_holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelector.onSelect(list.get(position).getId());
                }
            });
        }

        if (r==1)
        {
            Picasso.get().load(sList.get(position).getThumb()).into(holder.imgRelatedVideoThumb);
            holder.tvRelatedVideotitle.setText(sList.get(position).getTitle());
            holder.tvRelatedvideoDescription.setText(sList.get(position).getDescription());
            holder.ll_holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelector.onSelect(sList.get(position).getId());
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        if(r==0)
        {
            return list.size();
        }
        else if (r==1)
        {
            return sList.size();
        }
         return 0;
    }

    public void changeSelection(  ArrayList<RespVideos> selectedList,int indx)
    {
        //list.clear();
        //list.addAll(selectedList);
        sList=new ArrayList<>();
        sList.addAll(selectedList);
        sList.remove(indx);
        r=1;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgRelatedVideoThumb)
        public ImageView imgRelatedVideoThumb;

        @BindView(R.id.tvRelatedVideoTitle)
        public TextView tvRelatedVideotitle;

        @BindView(R.id.tvRelatedVideoDescription)
        public TextView tvRelatedvideoDescription;

        @BindView(R.id.ll_holder)
        LinearLayout ll_holder;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
