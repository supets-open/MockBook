package com.supets.pet.mvvm.rxbus;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class EventOberseve implements Observer<Object>, LifecycleObserver {

    private AppCompatActivity mContext;

    public EventOberseve(AppCompatActivity context) {
        this.mContext = context;
    }

    private boolean isstart = false;

    @Override
    public void onChanged(@Nullable Object s) {
        if (isstart) {
            isstart = false;
        } else {
            if (s instanceof EventType) {
                doEvent((EventType) s);
            }
            LiveBus.getInstance().removeObserver(this);
            LiveBus.getInstance().removeObservers(mContext);
        }
    }

    public void doEvent(EventType eventType) {
        if (mListener != null) {
            mListener.callBack(eventType);
        }
    }

    private EventCallBackListener mListener;

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        isstart = false;//在无值处理
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LiveBus.getInstance().removeObserver(this);
        LiveBus.getInstance().removeObservers(mContext);
    }

    public void startAsync(EventCallBackListener listener) {
        this.mListener = listener;
        isstart = true;
        LiveBus.getInstance().observe(mContext, this);
    }

    public void startAsyncForever(EventCallBackListener listener) {
        this.mListener = listener;
        isstart = true;
        LiveBus.getInstance().observeForever( this);
    }
}
