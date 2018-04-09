package com.supets.pet.mvvm.rxbus;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class EventAsyncOberseve implements Observer<Object>, LifecycleObserver {

    private AppCompatActivity mContext;

    public EventAsyncOberseve(AppCompatActivity context) {
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

        }
    }

    public void doEvent(EventType eventType) {
        if (mListener != null) {
            boolean isSuccess = mListener.callBack(eventType);
            if (isSuccess) {
                LiveBus.getInstance().removeObserver(this);
                mListener = null;
            }
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

    public void registerAsync(EventCallBackListener listener) {
        if (emptyCallBack()) {
            this.mListener = listener;
            isstart = true;
            LiveBus.getInstance().observe(mContext, this);
        }
    }

    public void registerAsyncForever(EventCallBackListener listener) {
        if (emptyCallBack()) {
            this.mListener = listener;
            isstart = true;
            LiveBus.getInstance().observeForever(this);
        }
    }

    private void resetFlag() {
        isstart = false;
    }

    public void postOberseve(EventType eventType) {
        resetFlag();
        LiveBus.getInstance().setValue(eventType);
    }

    public boolean emptyCallBack() {
        return mListener == null;
    }

}
