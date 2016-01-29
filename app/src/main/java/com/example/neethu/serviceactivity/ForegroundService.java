package com.example.neethu.serviceactivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;

import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by neethu on 29/1/16.
 */
public class ForegroundService extends Service {
    public static String LOG_TAG = "ForegroundService";

    public void onCreate() {
        super.onCreate();
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Contacts.ACTION.STARTFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Start Foreground Intent");
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(Contacts.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

            Intent previousIntent = new Intent(this, MainActivity.class);
            previousIntent.setAction(Contacts.ACTION.PREV_ACTION);
            PendingIntent ppreviousIntent = PendingIntent.getService(this, 0, previousIntent, 0);

            Intent playIntent = new Intent(this, MainActivity.class);
            playIntent.setAction(Contacts.ACTION.PLAY_ACTION);
            PendingIntent pplayIntent = PendingIntent.getService(this, 0, playIntent, 0);

            Intent nextIntent = new Intent(this, MainActivity.class);
            nextIntent.setAction(Contacts.ACTION.NEXT_ACTION);
            PendingIntent pnextIntent = PendingIntent.getService(this, 0, nextIntent, 0);
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_play);
            Notification notification = new NotificationCompat.Builder(this)

                    .setContentTitle("My MusicPlayer")
                    .setTicker("My MusicPlayer")
                    .setContentText("My Music")
                    .setLargeIcon(
                            Bitmap.createScaledBitmap(icon, 128, 128, false))
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .addAction(R.drawable.ic_prev, "previous", ppreviousIntent)
                    .addAction(R.drawable.ic_play, "play", pplayIntent)
                    .addAction(R.drawable.ic_next, "next", pnextIntent).build();

            startForeground(Contacts.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    notification);
        } else if (intent.getAction().equals(Contacts.ACTION.PREV_ACTION)) {
            Log.i(LOG_TAG, "Clicked Previous");
        } else if (intent.getAction().equals(Contacts.ACTION.PLAY_ACTION)) {
            Log.i(LOG_TAG, "Clicked Play");
        } else if (intent.getAction().equals(Contacts.ACTION.NEXT_ACTION)) {
            Log.i(LOG_TAG, "Clicked Next");
        } else if (intent.getAction().equals(
                Contacts.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();

        }

        return START_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
