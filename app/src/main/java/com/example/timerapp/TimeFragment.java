package com.example.timerapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeFragment extends Fragment {
    TextView tv_nowTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, container, false);

        // Inflate the layout for this fragment
        tv_nowTime = view.findViewById(R.id.tv_nowTime);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                        String formattedTime = formatter.format(date);
                        tv_nowTime.setText(formattedTime);
                    }
                }, 0, 1000);
            }
        });
    }
}