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

public class RemoteService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(RemoteService.this, LocalService.class), connection, Service.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new keepBinder();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(RemoteService.this, LocalService.class));
            bindService(new Intent(RemoteService.this, LocalService.class), connection, Service.BIND_IMPORTANT);
        }
    };

    private class keepBinder extends Binder {

    }
}
