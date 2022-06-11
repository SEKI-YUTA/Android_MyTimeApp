package com.example.timerapp.Models;

import java.io.Serializable;
import java.util.Date;

public class Alarm implements Serializable {
    private Date alarmTime;
    private boolean isActive;

    public Alarm(Date alarmTime, boolean isActive) {
        this.alarmTime = alarmTime;
        this.isActive = isActive;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
