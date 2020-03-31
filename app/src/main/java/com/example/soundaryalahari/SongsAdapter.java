package com.example.soundaryalahari;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {
     Context context;
     int no_of_items;
     int position;
     List<String> titles;
     List<String> songs;

    public SongsAdapter(Context context, int position,int no_of_items, List<String> titles,List<String> songs){
        this.context = context;
        this.position = position;
        this.no_of_items = no_of_items;
        this.titles = titles;
        this.songs = songs;
    }

    public class SongsViewHolder extends RecyclerView.ViewHolder {
        public ImageView im;
        public TextView t;
        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.play_pause_icon);
            t = itemView.findViewById(R.id.song_name);
        }
    }

    @NonNull
    @Override
    public SongsAdapter.SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item,parent,false);
        SongsAdapter.SongsViewHolder recycleViewHolder = new SongsAdapter.SongsViewHolder(v);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongsAdapter.SongsViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,PlayerActivity.class);
                i.putExtra("song", (Serializable) songs);
                i.putExtra("titles", (Serializable) titles);
//               i.putStringArrayListExtra("song", (ArrayList<String>) songs);
//               i.putStringArrayListExtra("titles", (ArrayList<String>) titles);
               i.putExtra("position",position);
               context.startActivity(i);
            }
        });
        holder.im.setImageResource(R.drawable.play);
        holder.t.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

}
