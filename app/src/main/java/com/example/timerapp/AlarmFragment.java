package com.example.timerapp;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment implements OnAlarmToggleListener {
    int numHour, numMin = 0;
    NumberPicker numPickerHour, numPickerMin;
    RecyclerView recyclerView;
    FloatingActionButton fab_addAlarm;
    List<Alarm> alarmList = new ArrayList<>();
    AlarmAdapter adapter;

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
                // とりあえず適当なデータを追加してみる
                Alarm newAlarm = new Alarm(String.format("%02d:%02d", numHour, numMin), false);
                alarmList.add(newAlarm);
                adapter.notifyDataSetChanged();
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
            Toast.makeText(getContext(), alarm.getTime() + "のタイマーをオンにしました", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), alarm.getTime() + "のタイマーをオフにしました", Toast.LENGTH_SHORT).show();
        }
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