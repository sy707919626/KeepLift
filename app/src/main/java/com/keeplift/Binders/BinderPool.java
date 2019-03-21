package com.keeplift.Binders;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.keeplift.IAnimal;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/3/20.
 */

public class BinderPool {
    private static final String TAG = "aaaaaaaa: ";
    public static final int NO_ANIMAL = 0;
    public static final int ANIMAL_CODE_BIRD = 1;
    public static final int ANIMAL_CODE_FISH = 2;
    public static final int ANIMAL_CODE_MONKEY = 3;

    private Context mContext;
    @SuppressWarnings("all")
    private static BinderPool sInstance;

    private CountDownLatch mCountDownLatch;
    private IAnimal mIAnimal;

    public BinderPool(Context context) {
        this.mContext = context;
        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context){
        if (sInstance == null){
            synchronized (BinderPool.class){
                if (sInstance == null){
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIAnimal = IAnimal.Stub.asInterface(service);
            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: ");
        }
    };

    private synchronized void connectBinderPoolService(){
        mCountDownLatch = new CountDownLatch(1);//线程同步处理
        Intent intent = new Intent(mContext, BinderService.class);
        mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);

        try {
            mCountDownLatch.await();
        } catch (InterruptedException  e){
            e.printStackTrace();
        }
    }

    public IBinder queryAnimal(int animalCode){
        IBinder binder =null;
        try{
            if (mIAnimal != null){
                binder = mIAnimal.queryAnimal(animalCode);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

    public static class AnimalBinder extends IAnimal.Stub{
        @Override
        public IBinder queryAnimal(int animalCode) throws RemoteException {
            IBinder binder = null;
            switch (animalCode) {
                case ANIMAL_CODE_BIRD:
                    binder = new BirdBinder();
                    break;
                case ANIMAL_CODE_FISH:
                    binder = new FishBinder();
                    break;
                case ANIMAL_CODE_MONKEY:
                    binder = new MonkeyBinder();
                    break;
                default:
                    break;
            }
            return binder;
        }
    }
}
