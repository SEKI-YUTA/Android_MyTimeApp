package com.example.timerapp.Listeners;

import com.example.timerapp.Models.Alarm;

public interface OnAlarmToggleListener {
    void toggleActive(boolean isActive, Alarm alarm);
    void onLongPressItem(Alarm alarm);
}
