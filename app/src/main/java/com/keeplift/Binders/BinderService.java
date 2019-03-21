package com.keeplift.Binders;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2019/3/20.
 */

public class BinderService extends Service {
    private BinderPool.AnimalBinder mBinder = new BinderPool.AnimalBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
