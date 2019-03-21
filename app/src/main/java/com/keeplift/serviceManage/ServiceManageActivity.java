package com.keeplift.serviceManage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.keeplift.IMyAidlInterface;
import com.keeplift.IMyCallbackListener;
import com.keeplift.R;

public class ServiceManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_manage);

        //通过1像素Activity 保活
//        KeepManager.getInstance().registerKeep(this);

        //通过前台服务 保活
//        startService(new Intent(this, ForegroundService.class));

        //双进程守护
//        startService(new Intent(this, LocalService.class));
//        startService(new Intent(this, RemoteService.class));

        test();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        KeepManager.getInstance().unregisterKeep(this);
    }

    private void test(){
        Intent intent = new Intent(ServiceManageActivity.this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

//    private MyService.MyBinder binder;
    private IMyAidlInterface mService;


    private IMyCallbackListener.Stub mListener = new IMyCallbackListener.Stub() {

        @Override
        public void onRespond(String str) throws RemoteException {
            Log.e("aaaaaaa  : ", "receive msg from service: "+str);
        }
    };
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            binder = (MyService.MyBinder) service;
//            binder.setOnTestListener(new MyService.MyBinder. OnTestListener(){
//                @Override
//                public void onTest(String str) {
//                    Log.e("aaaaaaa  : ", "receive msg from service: "+str);
//                }
//            });
            mService =  IMyAidlInterface.Stub.asInterface(service);

            try {
               mService.registerListener(mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void but(View view) throws RemoteException {
//        binder.testMethod("hi, service.");
        mService.testMethod("hi, service.");
    }

    public void unregis(View view) throws RemoteException {
        mService.unregisterListener(mListener);
    }
}
