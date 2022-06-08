package com.example.timerapp.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.timerapp.R;

public class MyMediaManager {

    private static MyMediaManager mediaManager = new MyMediaManager();
    private static MediaPlayer mediaPlayer1;
    public static MyMediaManager getInstance(Context context) {
        if(mediaPlayer1 == null) {
            mediaPlayer1 = MediaPlayer.create(context, R.raw.alarm1);
        }
        return mediaManager;
    }
    public void playSound1() {
        if(mediaPlayer1.isPlaying()) return;
        mediaPlayer1.start();
    }
    public void stopSound1() {
        if(!mediaPlayer1.isPlaying()) return;
        mediaPlayer1.stop();
    }
}
