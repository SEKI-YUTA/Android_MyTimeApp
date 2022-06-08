package com.example.timerapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.timerapp.Utils.MyMediaManager;
import com.example.timerapp.Utils.MyNotificationManager;

public class TimerFragment extends Fragment {
    int numHour, numMin, numSec;
    NumberPicker numPickerHour, numPickerMin, numPickerSec;
    Button btn_startAndStop, btn_cancel, btn_playSound, btn_stopSound, btn_pushNotification;
    TextView tv_remainTime;
    int hour, min;
    MyMediaManager mediaManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        numPickerHour = view.findViewById(R.id.numPickerHour);
        numPickerMin = view.findViewById(R.id.numPickerMin);
        numPickerSec = view.findViewById(R.id.numPickerSec);
        btn_startAndStop = view.findViewById(R.id.btn_startAndStop);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        // ここからはテスト用
        btn_playSound = view.findViewById(R.id.btn_playSound);
        btn_stopSound = view.findViewById(R.id.btn_stopSound);
        btn_pushNotification = view.findViewById(R.id.btn_pushNotification);
        btn_playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaManager = MyMediaManager.getInstance(getContext());
                mediaManager.playSound1();
            }
        });
        btn_stopSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaManager = MyMediaManager.getInstance(getContext());
                mediaManager.stopSound1();
            }
        });
        btn_pushNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyNotificationManager myNotificationManager = MyNotificationManager.getInstance(getContext());
                myNotificationManager.pushNotification("テスト用の通知です", "テスト用の通知のコンテント");
            }
        });
        // ここまでテスト用
        tv_remainTime = view.findViewById(R.id.tv_remainTime);
        setUpNumberPicker(numPickerHour);
        setUpNumberPicker(numPickerMin);
        setUpNumberPicker(numPickerSec);

        return view;
    }

    private void setUpNumberPicker(NumberPicker picker) {
        if (picker.getId() == R.id.numPickerHour) {
            picker.setMaxValue(24);
        } else {
            picker.setMaxValue(59);
        }
        picker.setMinValue(0);
        picker.setOnValueChangedListener(listener);
    }

    NumberPicker.OnValueChangeListener listener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int prevVal, int newVal) {
            // i prevVal
            // i1 newVal
            switch (numberPicker.getId()) {
                case R.id.numPickerHour:
                    Log.d("MyLog", "changed hour picker");
                    numHour = newVal;
                    break;
                case R.id.numPickerMin:
                    Log.d("MyLog", "changed min picker");
                    numMin = newVal;
                    break;
                case R.id.numPickerSec:
                    Log.d("MyLog", "changed min picker");
                    numSec = newVal;
                    break;
            }
        }
    };
}