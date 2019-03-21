package com.keeplift.Binders;

import android.os.IBinder;
import android.os.RemoteException;

import com.keeplift.IAnimal;

/**
 * 实现类
 */

public class AnimalBinder extends IAnimal.Stub{
    public static final int ANIMAL_CODE_BIRD = 1;
    public static final int ANIMAL_CODE_FISH = 2;
    public static final int ANIMAL_CODE_MONKEY = 3;

    @Override
    public IBinder queryAnimal(int animalCode) throws RemoteException {
        IBinder binder = null;
        switch (animalCode){
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
