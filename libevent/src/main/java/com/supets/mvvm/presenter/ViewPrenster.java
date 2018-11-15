package com.supets.mvvm.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.support.annotation.Keep;
import android.util.Log;

import com.supets.mvvm.di.InjectComponetUtils;
import com.supets.mvvm.view.IView;

/**
 * 模块解耦:事件管理者
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */
@Keep
public class ViewPrenster<T extends IView> implements LifecycleObserver {

    private T mView;

    public ViewPrenster(T view) {
        this.mView = view;
        InjectComponetUtils.injectComponentNo(this);
    }

    public T getView() {
        return mView;
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
