package com.momenalhendawy.myway;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final int BROADCAST_NOTIFICATION_ID = 1;

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String notificationBody = "";
        String notificationTitle = "";

        if (remoteMessage.getNotification().getBody() != null) {
            Log.i("PVL", "RECEIVED MESSAGE: " + remoteMessage.getNotification().getBody());
            notificationTitle = remoteMessage.getNotification().getTitle();
            notificationBody = remoteMessage.getNotification().getBody();
            showNotification(notificationTitle,notificationBody);
        }
        else {showNotification(remoteMessage.getData());}

        Log.d(TAG, "onMessageReceived: notification title: " + notificationTitle);
        Log.d(TAG, "onMessageReceived: notification body: " + notificationBody);

    }

    private void showNotification(Map<String,String> data) {
        String title =data.get("title");
        String body = data.get("body");
        String CHANNEL_ID = "my_channel_01";
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "Notification"
                    , mNotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("Information order");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            mChannel.setVibrationPattern(new long[]{0,1000,500,1000});

            mNotificationManager.createNotificationChannel(mChannel);

        }
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this,CHANNEL_ID);

        notification  .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("order Information");

        mNotificationManager.notify(new Random().nextInt(),notification.build());

    }

    void showNotification(String title, String message) {

        String CHANNEL_ID = "my_channel_01";
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "Notification"
                    , mNotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("Information order");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            mChannel.setVibrationPattern(new long[]{0,1000,500,1000});

            mNotificationManager.createNotificationChannel(mChannel);

        }
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this,CHANNEL_ID);

        notification  .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setContentInfo("order Information");

        mNotificationManager.notify(new Random().nextInt(),notification.build());

    }

}
