package com.example.timerapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

public class StopWatchFragment extends Fragment {
    Chronometer chronometer;
    Button btn_startAndStop, btn_reset;
    boolean isRunning = false;
    Long startTime = 0l;
    Long distanceTime = 0l;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        btn_startAndStop = view.findViewById(R.id.btn_startAndStop);
        btn_reset = view.findViewById(R.id.btn_reset);
        chronometer = view.findViewById(R.id.myChronometer);
        btn_startAndStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning) {
                    // 停止処理
                    Long time = SystemClock.elapsedRealtime();
                    distanceTime = time - startTime;
                    chronometer.stop();
                    isRunning = false;
                    btn_startAndStop.setText("再開");
                    btn_reset.setEnabled(true);
                } else {
                    // 開始処理
                    // この中に再開処理も書く
                    if(startTime != 0l ) {
                        // 再開処理
                        Long time = SystemClock.elapsedRealtime();
                        startTime = time - distanceTime;
                        chronometer.setBase(time - distanceTime);
                    } else {
                        // 開始処理
                        Long time = SystemClock.elapsedRealtime();
                        startTime = time;
                        chronometer.setBase(time);
                    }
                    chronometer.start();
                    isRunning = true;
                    btn_startAndStop.setText("一時停止");
                    btn_reset.setEnabled(false);

                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = 0l;
                distanceTime = 0l;
                chronometer.stop();
                chronometer.setText("00:00");
                btn_startAndStop.setText("スタート");
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
    }
}