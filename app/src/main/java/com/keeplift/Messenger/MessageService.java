package com.keeplift.Messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2019/3/20.
 */

public class MessageService extends Service {
    private static final String TAG = "aaaaaaaaaa:";

    //1.Service里面实现一个Handler用来接收消息用
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //6.Service里面的Handler收到消息并处理
            if (msg.what == 1){
                Bundle bundle = msg.getData();
                Log.e(TAG, "receive message from activity: "+bundle.getString("string"));
            }

            //9.取出消息中的Messenger对象
            Messenger replyMessenger  = msg.replyTo;

            Message replyMsg = new Message();
            replyMsg.what = 2;
            Bundle b = new Bundle();
            b.putString("string", "hi, activity");
            replyMsg.setData(b);

            try{
                // 10.使用Messenger给Activity发消息
                replyMessenger.send(replyMsg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    };

    //2.使用这个Handler创建一个Messenger对象
    private Messenger mMessenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //3.使用这个Messenger对象创建一个Binder对象，并在onBind方法返回
        return mMessenger.getBinder();
    }
}
