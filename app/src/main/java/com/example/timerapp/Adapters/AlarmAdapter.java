package com.example.timerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timerapp.Listeners.OnAlarmToggleListener;
import com.example.timerapp.Models.Alarm;
import com.example.timerapp.R;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    Context context;
    List<Alarm> alarmList;
    OnAlarmToggleListener listener;

    public AlarmAdapter(Context context, List<Alarm> alarmList, OnAlarmToggleListener listener) {
        this.context = context;
        this.alarmList = alarmList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlarmViewHolder(LayoutInflater.from(context).inflate(R.layout.alarm_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(holder.getAdapterPosition());
        holder.tv_alarmItem_Time.setText(String.format("%02d:%02d", alarm.getAlarmTime().getHours(), alarm.getAlarmTime().getMinutes()));
        holder.alarmItem_toggleActive.setChecked(alarm.isActive());

        holder.alarmItem_toggleActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listener.toggleActive(b, alarm);
            }
        });

        holder.alarmItem_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLongPressItem(alarmList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {
        CardView alarmItem_container;
        Switch alarmItem_toggleActive;
        TextView tv_alarmItem_Time;
        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmItem_container = itemView.findViewById(R.id.alarmItem_container);
            alarmItem_toggleActive = itemView.findViewById(R.id.alarmItem_toggleActive);
            tv_alarmItem_Time = itemView.findViewById(R.id.tv_alarmItem_Time);

        }
    }
}
