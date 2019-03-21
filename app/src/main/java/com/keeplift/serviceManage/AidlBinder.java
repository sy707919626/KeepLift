package com.keeplift.serviceManage;

import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.keeplift.IMyAidlInterface;
import com.keeplift.IMyCallbackListener;
import com.keeplift.serviceManage.MyService;

/**
 * Created by Administrator on 2019/3/20.
 */

public class AidlBinder extends IMyAidlInterface.Stub {
    private MyService myService;
//    private IMyCallbackListener listener;
    private RemoteCallbackList<IMyCallbackListener> mListenerList = new RemoteCallbackList<>();

    public AidlBinder(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void testMethod(String str) throws RemoteException {
        myService.serviceMethod(str);

        //调用listener里面所有已注册的监听
        int count = mListenerList.beginBroadcast();
        for (int i = 0; i <count; i++){
            mListenerList.getBroadcastItem(i).onRespond("hi, activity");
        }

//        listener.onRespond("hi, activity");
    }

    @Override
    public void registerListener(IMyCallbackListener listener) throws RemoteException {
//        this.listener = listener;

        mListenerList.register(listener);
    }

    @Override
    public void unregisterListener(IMyCallbackListener listener) throws RemoteException {
        mListenerList.unregister(listener);
    }


}
