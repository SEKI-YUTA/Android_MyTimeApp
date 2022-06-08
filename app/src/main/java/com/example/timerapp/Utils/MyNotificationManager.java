package com.example.timerapp.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.timerapp.R;

public class MyNotificationManager {
    private static Context ManagerContext;
    private static MyNotificationManager instance = new MyNotificationManager();
    private static NotificationManager manager;
    private static String NAME = "Timer App Notification";
    private static String ID = "TimerAppNotify_Channel";
    private static String NOTIFY_DESCRIPTION = "This app send notification when remain timer or alarm";
    //    private static Notification notification;
    private static NotificationCompat.Builder notificationBuilder;
    private static NotificationChannel notificationChannel;


    public static MyNotificationManager getInstance(Context context) {
        ManagerContext = context;
        if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)) return instance;
        if (manager == null) {
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (manager.getNotificationChannel(ID) == null) {
            notificationChannel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(NOTIFY_DESCRIPTION);
            manager.createNotificationChannel(notificationChannel);
        }
        if (notificationBuilder == null) {
            notificationBuilder = new NotificationCompat.Builder(context, ID)
                    .setSmallIcon(R.drawable.ic_info);
        }
        return instance;
    }

    public void pushNotification(String title, String content) {
        if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)) {
            Toast.makeText(ManagerContext, "お使いのOSに対応していません", Toast.LENGTH_SHORT).show();
            return;
        }
        ;
        Notification notification = notificationBuilder
                .setContentTitle(title)
                .setContentText(content)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        manager.notify(1, notification);
        Log.d("MyLog", "pushNotification");

    }
}
