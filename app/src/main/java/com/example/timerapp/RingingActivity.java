package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timerapp.Models.Alarm;
import com.example.timerapp.Utils.MyMediaManager;

import java.util.Date;

public class RingingActivity extends AppCompatActivity {
    TextView tv_ringingTime;
    Button btn_ringingStop;
    MyMediaManager mediaManager;

    @Override
    public void onBackPressed() {
        // 戻るボタンで戻ってほしくないため
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringing);

        tv_ringingTime = findViewById(R.id.tv_ringingTime);
        btn_ringingStop = findViewById(R.id.btn_ringingStop);

        Alarm alarm = (Alarm) getIntent().getSerializableExtra("alarm");
        Date alarmTime = alarm.getAlarmTime();
        mediaManager = MyMediaManager.getInstance(this);

        tv_ringingTime.setText(String.format("%02d:%02d", alarmTime.getHours(), alarmTime.getMinutes()));



        btn_ringingStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaManager.stopSound1();
                mediaManager.releaseSound();
                finish();
            }
        });


    }
}