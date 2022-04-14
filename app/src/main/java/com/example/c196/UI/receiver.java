package com.example.c196.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.c196.R;

public class receiver extends BroadcastReceiver {
    String channel_ID ="C196";
    static int notificationID;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Toast.makeText(context,intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
        createNotification(context,channel_ID);

        Notification n=new NotificationCompat.Builder(context,channel_ID).setSmallIcon(R.drawable.ic_launcher_foreground).setContentText(intent.getStringExtra("key")).setContentTitle("Student Scheduler Notification").build();

        NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(notificationID++,n);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createNotification(Context context, String CHANNEL_ID){
        CharSequence name = context.getResources().getString(R.string.channel_name);
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel =new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}