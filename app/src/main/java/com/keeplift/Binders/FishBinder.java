package com.keeplift.Binders;

import android.os.RemoteException;
import android.util.Log;

import com.keeplift.IFish;

/**
 * 实现类
 */

public class FishBinder extends IFish.Stub{
    @Override
    public void swim() throws RemoteException {
        Log.e("aaaaaaa: ", "I'm fish, I can swim.");
    }
}
