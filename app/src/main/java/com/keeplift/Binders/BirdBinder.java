package com.keeplift.Binders;

import android.os.RemoteException;
import android.util.Log;

import com.keeplift.IBird;
/**
 * 实现类
 */

public class BirdBinder extends IBird.Stub {
    @Override
    public void fly() throws RemoteException {
        Log.e("aaaaaaa: ", "I'm bird, I can fly.");
    }
}
