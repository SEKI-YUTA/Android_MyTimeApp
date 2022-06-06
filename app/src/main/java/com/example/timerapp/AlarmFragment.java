package com.example.timerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.timerapp.Adapters.AlarmAdapter;
import com.example.timerapp.Listeners.OnAlarmToggleListener;
import com.example.timerapp.Models.Alarm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment implements OnAlarmToggleListener {
    RecyclerView recyclerView;
    FloatingActionButton fab_addAlarm;
    List<Alarm> alarmList = new ArrayList<>();
    AlarmAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        fab_addAlarm = view.findViewById(R.id.fab_addAlarm);

        adapter = new AlarmAdapter(getContext(), alarmList, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);

        fab_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add Alarm


                // とりあえず適当なデータを追加してみる
                Alarm newAlarm = new Alarm("07:00", false);
                alarmList.add(newAlarm);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void toggleActive(boolean isActive, Alarm alarm) {
        if (isActive) {
            Toast.makeText(getContext(), alarm.getTime() + "のタイマーをオンにしました", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), alarm.getTime() + "のタイマーをオフにしました", Toast.LENGTH_SHORT).show();
        }
    }
}