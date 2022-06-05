package com.example.timerapp;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Locale;

public class TimerFragment extends Fragment {
    Button btn_test;
    MediaPlayer mediaPlayer;
    int hour, min;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.alarm1);
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        btn_test = view.findViewById(R.id.btn_test);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour = i;
                min = i1;
                btn_test.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, min));
            }
        };

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(mediaPlayer.isPlaying()) return;
//                mediaPlayer.start();
                TimePickerDialog dialog = new TimePickerDialog(getContext(), listener, hour, min , true);
                dialog.show();
            }
        });




        return view;
    }
}