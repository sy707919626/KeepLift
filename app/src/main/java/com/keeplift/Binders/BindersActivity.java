package com.keeplift.Binders;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.keeplift.IBird;
import com.keeplift.IFish;
import com.keeplift.IMonkey;
import com.keeplift.R;


/**
 * binder连接池
 */
public class BindersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binders);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private BinderPool mBinderPool;
    public void bit1(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mBinderPool = BinderPool.getInstance(BindersActivity.this);
                IBinder birdBinder = mBinderPool.queryAnimal(BinderPool.ANIMAL_CODE_BIRD);
                IBinder fishBinder = mBinderPool.queryAnimal(BinderPool.ANIMAL_CODE_FISH);
                IBinder monkeyBinder = mBinderPool.queryAnimal(BinderPool.ANIMAL_CODE_MONKEY);

                IBird bird = IBird.Stub.asInterface(birdBinder);
                IFish fish = IFish.Stub.asInterface(fishBinder);
                IMonkey monkey = IMonkey.Stub.asInterface(monkeyBinder);

                try{
                    bird.fly();
                    fish.swim();
                    monkey.climbTree();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
