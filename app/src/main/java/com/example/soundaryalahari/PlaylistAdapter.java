package com.example.soundaryalahari;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>{
    List<String> songs;
    private int no_of_items;
    Context context;

    public PlaylistAdapter(Context context,int no_of_items,List<String> songs){
          this.context = context;
          this.no_of_items = no_of_items;
          this.songs = songs;
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder{
        public ImageView im;
        public TextView t;
        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.play_pause_icon);
            t = itemView.findViewById(R.id.song_name);
        }
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item,parent,false);
        PlaylistAdapter.PlaylistViewHolder recycleViewHolder = new PlaylistAdapter.PlaylistViewHolder(v);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaylistViewHolder holder, final int position) {
              int up_pos = position + 1;
              holder.itemView.setOnClickListener(new View.OnClickListener(){
                  @Override
                  public void onClick(View v) {
                      SongsFragment fragment = new SongsFragment();
                      Bundle bundle = new Bundle();
                      bundle.putInt("position",position);
                      fragment.setArguments(bundle);
                      ((MainActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frag,fragment).addToBackStack(null).commit();
                  }
              });

              holder.t.setText(songs.get(position));
              holder.im.setImageResource(R.drawable.forward);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

}
