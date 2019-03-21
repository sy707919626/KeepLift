package com.keeplift.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * 前台进程保活
 */

public class ForegroundService extends Service {
    private static final int SERVICE_ID = 1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       if (Build.VERSION.SDK_INT < 18){
           //4.0
           startForeground(SERVICE_ID, new Notification());
       } else if (Build.VERSION.SDK_INT < 26){
           //7.0
           startForeground(SERVICE_ID, new Notification());
           //删除通知栏消息
           startService(new Intent(this,InnerService.class));
       }else {
           //设置channel
           NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //设置最小优先级，通知重要性越低
           NotificationChannel channel = new NotificationChannel("channel","xxx", NotificationManager.IMPORTANCE_MIN);

           if (null != manager){
               manager.createNotificationChannel(channel);
               Notification notification = new NotificationCompat.Builder(this,"channel").build();

               startForeground(SERVICE_ID, notification);
           }
       }


        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnerService extends Service{
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
