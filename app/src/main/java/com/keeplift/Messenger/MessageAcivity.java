package com.keeplift.Messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.keeplift.R;

/**
 * Created by Administrator on 2019/3/20.
 */

public class MessageAcivity extends AppCompatActivity {
    private static final String TAG = "aaaaaaa: ";

    private Messenger mMessenger;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //4.Activity里面绑定Service的时候使用传过来的Binder创建一个Messenger对象
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //7.Activity里面实现一个Handler用来接收Service回复的消息
    private Handler mGetRelyMsg = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //11.处理Service回复的消息
            if (msg.what == 2){
                Bundle bundle = msg.getData();
                Log.e(TAG, "receive message from service: "+ bundle.getString("string"));
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messager);

        Intent intent = new Intent(this, MessageService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    public void fasong(View view) {
        Message msg = new Message();
        msg.what =1;

        Bundle bundle = new Bundle();
        bundle.putString("string", "hi, service");
        msg.setData(bundle);

        //8.发送消息的时候携带一个Messenger对象
        msg.replyTo = new Messenger(mGetRelyMsg);
        try {
            //5.Activity里面使用这个Messenger对象给Service发消息
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
