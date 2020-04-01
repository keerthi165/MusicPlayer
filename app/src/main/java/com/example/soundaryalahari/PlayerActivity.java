package com.example.soundaryalahari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import com.example.soundaryalahari.databinding.ActivityPlayerBinding;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PlayerActivity extends AppCompatActivity{
    ActivityPlayerBinding playerBinding;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;

    List <String> songs;
    List<String> song_titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerBinding = DataBindingUtil.setContentView(this,R.layout.activity_player);
        playerBinding.setLifecycleOwner(this);
        seekBar = playerBinding.seekBar;
        final int pos = getIntent().getIntExtra("position", 0);
        final int[] position = {pos};
        songs = (List<String>) getIntent().getSerializableExtra("song");
        song_titles = (List<String>) getIntent().getSerializableExtra("titles");

        playerBinding.songTitle.setText(song_titles.get(position[0]));
        playerBinding.playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    playerBinding.playPause.setImageResource(R.drawable.play);
                    mediaPlayer.pause();
                }
                else{
                    playerBinding.playPause.setImageResource(R.drawable.pause);
                    mediaPlayer.start();
                }
            }
        });
        if(position[0] == 0){
            playerBinding.previous.setImageResource(R.drawable.previous_disabled);
        }
        if(position[0] == songs.size()-1){
            playerBinding.next.setImageResource(R.drawable.next_disabled);
        }
        playerBinding.previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position[0] != 0) {
                    seekBar.setProgress(0);
                    position[0] = position[0] - 1;
                    playerBinding.playPause.setImageResource(R.drawable.pause);
                    playerBinding.songTitle.setText(song_titles.get(position[0]));
                    if(position[0] == 0){
                        playerBinding.previous.setImageResource(R.drawable.previous_disabled);
                    }
                    else {
                        playerBinding.previous.setImageResource(R.drawable.previous);
                    }
                    playerBinding.next.setImageResource(R.drawable.next);
                    int resId = getResources().getIdentifier(songs.get(position[0]),
                            "raw", getPackageName());
                   mediaPlayer.stop();
                   mediaPlayer = MediaPlayer.create(getApplicationContext(),resId);
                   mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            onComplete(position[0],mp);
                        }
                    });
                   seekBar.setMax(mediaPlayer.getDuration());
                }
                else{
                    playerBinding.previous.setImageResource(R.drawable.previous_disabled);
                }
            }
        });

        playerBinding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position[0] !=songs.size()-1){
                    seekBar.setProgress(0);
                    playerBinding.playPause.setImageResource(R.drawable.pause);
                        position[0] = position[0] + 1;
                          if(position[0]==songs.size()-1){
                              playerBinding.next.setImageResource(R.drawable.next_disabled);
                          }
                          else {
                              playerBinding.next.setImageResource(R.drawable.next);
                          }
                   playerBinding.previous.setImageResource(R.drawable.previous);
                    playerBinding.songTitle.setText(song_titles.get(position[0]));
                        int resId = getResources().getIdentifier(songs.get(position[0]),
                                "raw", getPackageName());
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(getApplicationContext(),resId);
                        mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                           onComplete(position[0],mp);
                        }
                    });
                        seekBar.setMax(mediaPlayer.getDuration());
                }
                else{
                    playerBinding.next.setImageResource(R.drawable.next_disabled);
                }
            }
        });
        int resId = getResources().getIdentifier(songs.get(position[0]),
                "raw", getPackageName());
        handler = new Handler();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),resId);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mediaPlayer.getDuration());
                playCycle();
                mp.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                onComplete(position[0],mp);
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playCycle(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if(mediaPlayer.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        playerBinding.playPause.setImageResource(R.drawable.pause);
        mediaPlayer.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }

    public void onComplete(int position,MediaPlayer mp){
        if(position!=songs.size()-1){
            playerBinding.next.performClick();
        }
        else {
            playerBinding.playPause.setImageResource(R.drawable.play);
            mp.start();
            playerBinding.playPause.setImageResource(R.drawable.pause);
        }
    }

}
