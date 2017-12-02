package com.example.kbiid.termproject;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by kbiid on 2017-12-02.
 */

public class Music {

    private MediaPlayer mediaPlayer;

    public Music(Context context, int songUri){
        mediaPlayer = MediaPlayer.create(context, songUri);
    }

    public void musicStart(){
        mediaPlayer.start();
    }

    public void musicStop(){
        mediaPlayer.stop();
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    public int getCurrent(){
        return mediaPlayer.getCurrentPosition();
    }
}
