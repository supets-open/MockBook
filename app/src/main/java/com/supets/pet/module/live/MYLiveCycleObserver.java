package com.supets.pet.module.live;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class MYLiveCycleObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void liveData() {
        Log.v("livedata", "ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void liveData2() {
        Log.v("livedata", "ON_CREATE");
    }

}
