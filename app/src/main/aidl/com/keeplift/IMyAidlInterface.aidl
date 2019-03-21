// IMyAidlInterface.aidl

package com.keeplift;
import com.keeplift.IMyCallbackListener;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
  void testMethod(String str);
  void registerListener(IMyCallbackListener listener);
   void unregisterListener(IMyCallbackListener listener);
}
