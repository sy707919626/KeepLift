package com.keeplift.Binders;

import android.os.RemoteException;
import android.util.Log;

import com.keeplift.IMonkey;

/**
 * 实现类
 */

public class MonkeyBinder extends IMonkey.Stub {
    @Override
    public void climbTree() throws RemoteException {
        Log.e("aaaaaaa: ", "I'm monkey, I can climb the tree.");
    }
}
