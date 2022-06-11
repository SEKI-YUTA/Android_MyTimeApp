package com.example.timerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.timerapp.Adapters.AlarmAdapter;
import com.example.timerapp.Listeners.OnAlarmToggleListener;
import com.example.timerapp.Models.Alarm;
import com.example.timerapp.Utils.MyMediaManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmFragment extends Fragment implements OnAlarmToggleListener {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.JAPAN);
    int numHour, numMin = 0;
    MyMediaManager mediaManager;
    NumberPicker numPickerHour, numPickerMin;
    RecyclerView recyclerView;
    FloatingActionButton fab_addAlarm;
    List<Alarm> alarmList = new ArrayList<>();
    AlarmAdapter adapter;
    Timer alarmTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        numPickerHour = view.findViewById(R.id.numPickerHour);
        numPickerMin = view.findViewById(R.id.numPickerMin);
        recyclerView = view.findViewById(R.id.recyclerview);
        fab_addAlarm = view.findViewById(R.id.fab_addAlarm);

        setUpNumberPicker(numPickerHour);
        setUpNumberPicker(numPickerMin);

        adapter = new AlarmAdapter(getContext(), alarmList, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);

        fab_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add Alarm
                Date now = new Date();
                Date alarmDate;
                Log.d("MyLog", String.valueOf(now.getMonth()));
                Log.d("MyLog", String.valueOf(now.getDate()));
                Log.d("MyLog", String.valueOf(now.getYear()));

                Log.d("MyLog", dateFormat.format(now));
                try {
                    alarmDate = dateFormat.parse(String.format("%04d-%02d-%02d %02d:%02d", now.getYear() + 1900, now.getMonth() + 1, now.getDate(), numHour, numMin));
                    if(alarmDate.before(now)) {
                        alarmDate = new Date(alarmDate.getTime() + (60*60*24*1000));
                    }
                    Alarm newAlarm = new Alarm(alarmDate, false);
                    alarmList.add(newAlarm);
                    adapter.notifyDataSetChanged();
                    Log.d("MyLog", "next ring is " + dateFormat.format(alarmDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }



            }
        });

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

    @Override
    public void toggleActive(boolean isActive, Alarm alarm) {
        if (isActive) {
            Toast.makeText(getContext(), dateFormat.format(alarm.getAlarmTime()) + "のタイマーをオンにしました", Toast.LENGTH_SHORT).show();
            alarmTimer = new Timer();
            alarmTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Date now = new Date();
                    int index = alarmList.indexOf(alarm);
                    Date alarmTime = alarmList.get(index).getAlarmTime();
                    if(now.getTime() > alarmTime.getTime()) {
                        mediaManager = MyMediaManager.getInstance(getContext());
                        mediaManager.playSound1();
                        Intent intent = new Intent(getContext(), RingingActivity.class);
                        intent.putExtra("alarm", alarm);
                        startActivity(intent);
                        this.cancel();
                    }
                }
            }, 0, 1000);
        } else {
            Toast.makeText(getContext(), dateFormat.format(alarm.getAlarmTime()) + "のタイマーをオフにしました", Toast.LENGTH_SHORT).show();
            if(alarmTimer != null) {
                alarmTimer.cancel();
                alarmTimer = null;
            }
        }
    }

    @Override
    public void onLongPressItem(Alarm alarm) {
        Date alarmDate = alarm.getAlarmTime();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(String.format("%02d:%02d", alarmDate.getHours(), alarmDate.getMinutes()) + "のアラームを削除しますか？")
                        .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alarmList.remove(alarm);
                                adapter.notifyDataSetChanged();
                            }
                        })
                .setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                }).show();

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
            }
        }
    };
}