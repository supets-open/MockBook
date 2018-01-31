package com.supets.pet.module.live;

import android.arch.lifecycle.LiveData;
import android.util.Log;


public class MYLiveData extends LiveData<String> {


    //当 LiveData 有一个处于活动状态的观察者时该方法被调用
    @Override
    protected void onActive() {
        //开始定位
        Log.v("livedata", "onActive");
    }

    //当 LiveData 没有任何处于活动状态的观察者时该方法被调用
    @Override
    protected void onInactive() {
        //  结束定位
        Log.v("livedata", "onInactive");
    }

    public void updateData(String name) {
        this.postValue(name);
        Log.v("livedata", "postValue");
    }

}
