package com.keeplift.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2019/3/20.
 */

public class LocalService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new keepBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(LocalService.this, RemoteService.class), connection, Service.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection connection = new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {

       }

       @Override
       public void onServiceDisconnected(ComponentName name) {
           startService(new Intent(LocalService.this, RemoteService.class));
           bindService(new Intent(LocalService.this, RemoteService.class), connection, Service.BIND_IMPORTANT);
       }
   };

    private class keepBinder extends Binder{

    }
}
