package com.keeplift.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2019/3/20.
 */

public class KeepManager {
    private static final KeepManager ourInstance = new KeepManager();

    public static KeepManager getInstance(){
        return ourInstance;
    }

    public KeepManager() {
    }

    private KeepReceiver keepReceiver;

    private WeakReference<Activity> mKeepAct; //弱引用

    /**
     * 注册开屏关屏
     */
    public void registerKeep(Context context){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        keepReceiver = new KeepReceiver();
        context.registerReceiver(keepReceiver,filter);
    }

    /**
     * 注销
     */
    public void unregisterKeep(Context context){
        if (keepReceiver != null){
            context.unregisterReceiver(keepReceiver);
        }
    }

    //开启1像素Acitivity
    public void startKeep(Context context){
        Intent intent = new Intent(context, SinglePixelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //关闭1像素Acitivity
    public void finishKeep(){
        if (null != mKeepAct){
            Activity activity = mKeepAct.get();
            if (null  != null){
                activity.finish();
            }

            mKeepAct = null;
        }
    }

    public void setKeep(SinglePixelActivity singlePixelActivity){
        mKeepAct = new WeakReference<Activity>(singlePixelActivity);
    }
}
