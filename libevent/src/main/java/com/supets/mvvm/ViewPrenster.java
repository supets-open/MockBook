package com.supets.mvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.support.annotation.Keep;
import android.util.Log;

/**
 * 模块解耦:事件管理者
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */
@Keep
public abstract class ViewPrenster<T extends ViewProxy> implements LifecycleObserver {

    protected T mView;

    protected abstract void init();

    public ViewPrenster(T view) {
        this.mView = view;
        ViewModelDI.injectComponentNo(this);
        init();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.v("ViewPrenster", "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.v("ViewPrenster", "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.v("ViewPrenster", "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.v("ViewPrenster", "onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        Log.v("ViewPrenster", "onDestory");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.v("ViewPrenster", "onCreate");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }


}
