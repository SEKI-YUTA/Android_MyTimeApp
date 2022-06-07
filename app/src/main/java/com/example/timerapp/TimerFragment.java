package com.example.timerapp;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Locale;

public class TimerFragment extends Fragment {
    int numHour, numMin, numSec;
    NumberPicker numPickerHour, numPickerMin, numPickerSec;
    Button btn_startAndStop, btn_cancel;
    TextView tv_remainTime;
    MediaPlayer mediaPlayer;
    int hour, min;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.alarm1);
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        numPickerHour = view.findViewById(R.id.numPickerHour);
        numPickerMin = view.findViewById(R.id.numPickerMin);
        numPickerSec = view.findViewById(R.id.numPickerSec);
        btn_startAndStop = view.findViewById(R.id.btn_startAndStop);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        tv_remainTime = view.findViewById(R.id.tv_remainTime);


        setUpNumberPicker(numPickerHour);
        setUpNumberPicker(numPickerMin);
        setUpNumberPicker(numPickerSec);

        return view;
    }

    private void setUpNumberPicker(NumberPicker picker) {
        if(picker.getId() == R.id.numPickerHour) {
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