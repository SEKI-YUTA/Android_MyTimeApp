package com.example.timerapp;

import static com.example.timerapp.AlarmFragment.dateFormat;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.timerapp.Utils.MyMediaManager;
import com.example.timerapp.Utils.MyNotificationManager;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment {
    int numHour, numMin, numSec = 0;
    boolean isRinging;
    NumberPicker numPickerHour, numPickerMin, numPickerSec;
    Button btn_startAndStop, btn_cancel;
    TextView tv_remainTime;
    MyMediaManager mediaManager;
    Timer timerSchedule;
    Date timerDate;
    Handler handler = new Handler();

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
//        btn_pushNotification = view.findViewById(R.id.btn_pushNotification);
//        btn_pushNotification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyNotificationManager myNotificationManager = MyNotificationManager.getInstance(getContext());
//                myNotificationManager.pushNotification("テスト用の通知です", "テスト用の通知のコンテント");
//            }
//        });
        // ここまでテスト用
        tv_remainTime = view.findViewById(R.id.tv_remainTime);
        setUpNumberPicker(numPickerHour);
        setUpNumberPicker(numPickerMin);
        setUpNumberPicker(numPickerSec);

        btn_startAndStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerDate != null) return;
                Log.d("MyLog", "timerDate is not null");
                if(isRinging) {
                    Log.d("MyLog", "Stopping timer");
                    mediaManager = MyMediaManager.getInstance(getContext());
                    mediaManager.stopSound1();
                    mediaManager.releaseSound();
                    btn_startAndStop.setText("スタート");
                    isRinging = false;
                    return;
                }
                Log.d("MyLog", "SetTimer");
                Date now = new Date();
                final long[] displayTime = {0};
                long timerTime = now.getTime() + (numSec * 1000) + (numMin * 60 * 1000) + (numHour * 60 * 60 * 1000);
                timerDate = new Date(timerTime);
                Log.d("MyLog", "timerDate" +  dateFormat.format(timerDate));
                Log.d("MyLog", "nowDate" +  dateFormat.format(now));
                timerSchedule = new Timer();
                timerSchedule.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Date nowTime = new Date();
                        if(nowTime.getTime() > timerDate.getTime() ) {
                            mediaManager = MyMediaManager.getInstance(getContext());
                            mediaManager.playSound1();
                            timerDate = null;
                            isRinging = true;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_remainTime.setText("00:00:00");
                                    btn_startAndStop.setText("ストップ");
                                }
                            });
                            this.cancel();
                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    displayTime[0] = timerDate.getTime() - nowTime.getTime();
                                    int hour = (int) (displayTime[0] / 3600000);
                                    int min = (int) ((displayTime[0]%3600000) / 60000);
                                    int sec = (int) (displayTime[0] % 60000) / 1000;

                                    Log.d("MyLog", String.format("%02d:%02d:%02d", hour, min, sec));
                                    tv_remainTime.setText(String.format("%02d:%02d:%02d", hour, min, sec + 1));
                                }
                            });
                        }
                    }
                }, 0 , 1000);

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerSchedule.cancel();
                tv_remainTime.setText("00:00:00");
            }
        });

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
                    Log.d("MyLog", "changed sec picker");
                    numSec = newVal;
                    break;
            }
        }
    };
}