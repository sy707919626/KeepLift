package com.keeplift.serviceManage;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2019/3/20.
 */

public class MyService extends Service {
    // 第4步，实例化一个MyBinder对象
//    private MyBinder mBinder = new MyBinder(this);

    private AidlBinder binder = new AidlBinder(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        return mBinder;
        return binder;
    }

    public void serviceMethod(String str){
        Log.e("aaaaaaa", "receive msg from activity: " + str);
    }


//    public static class MyBinder extends Binder {
//        private MyService mService;
//        private OnTestListener mListener;
//
//        public MyBinder(MyService mService) {
//            this.mService = mService;
//        }
//
//        public void testMethod(String str){
//            // Activity通过Binder来调用Service的方法将消息传给Service
//            mService.serviceMethod(str);
//            // 并回调mListener.onTest告诉Activity已收到消息
//            mListener.onTest("hi, activity.");
//        }
//
//        public void setOnTestListener(OnTestListener listener){
//            this.mListener = listener;
//        }
//
//        //自定义一个回调接口
//        public interface OnTestListener {
//            void onTest(String str);
//        }
//    }



}
