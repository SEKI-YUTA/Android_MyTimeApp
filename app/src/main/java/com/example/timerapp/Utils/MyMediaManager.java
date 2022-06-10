package com.example.timerapp.Utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.Settings;

import com.example.timerapp.R;

public class MyMediaManager {

    private static MyMediaManager mediaManager = new MyMediaManager();
    private static MediaPlayer mediaPlayer1;
    private static Context MediaContext;
    public static MyMediaManager getInstance(Context context) {
        if(mediaPlayer1 == null) {
            MediaContext = context;
            mediaPlayer1 = MediaPlayer.create(context, R.raw.alarm1);
            // another way
//            mediaPlayer1 = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
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
    public void releaseSound() {
        if(mediaPlayer1.isPlaying()) return;
        mediaPlayer1.release();
        mediaPlayer1 = MediaPlayer.create(MediaContext, R.raw.alarm1);
    }
}
