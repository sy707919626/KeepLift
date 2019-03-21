package com.keeplift.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Keep;
import android.text.TextUtils;

/**
 * Created by Administrator on 2019/3/20.
 */

public class KeepReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)){
            //关屏
            KeepManager.getInstance().startKeep(context);
        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)){
            //开屏
            KeepManager.getInstance().finishKeep();

        }
    }
}
