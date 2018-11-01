package com.petsearch.visakhdemoapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.petsearch.visakhdemoapp.Controller.Presenter;
import com.petsearch.visakhdemoapp.Model.Movies;
import com.petsearch.visakhdemoapp.Model.Result;
import com.petsearch.visakhdemoapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    String desc;
    Presenter presenter;
    int pos;
    String id;
    private List<Result> list = new ArrayList<>();
    public RecyclerAdapter(Context context, List<Result> list, Presenter mPresenter){
        this.context = context;
        this.list = list;
        this.presenter = mPresenter;

    }
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getTitle());

//        pos = position;
        if(list.get(position).getOverview().length()>90) {

            desc = list.get(position).getOverview().substring(0,90);
        }
        else {
            desc = list.get(position).getOverview();
        }
        holder.des.setText(desc);
        holder.rating.setText(list.get(position).getVoteAverage().toString());
        holder.lang.setText(list.get(position).getOriginalLanguage());
        holder.date.setText(list.get(position).getReleaseDate());

        pos = list.get(position).getId();

        Log.e("url",""+list.get(position).getBackdropPath());

        Glide.with(context).load(list.get(position).getBackdropPath())
                .thumbnail(0.5f)
                .crossFade()
                .fitCenter()
                .placeholder(R.drawable.loading_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,des,rating,lang,date;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.title);
            des = (TextView)itemView.findViewById(R.id.des);
            rating = (TextView)itemView.findViewById(R.id.rating);
            lang = (TextView)itemView.findViewById(R.id.lang);
            date = (TextView)itemView.findViewById(R.id.date);
            img = (ImageView) itemView.findViewById(R.id.img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (presenter != null) {
                presenter.onClick2(pos);
            }
        }
    }

}

