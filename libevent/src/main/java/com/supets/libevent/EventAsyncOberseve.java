package com.supets.libevent;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;


public class EventAsyncOberseve implements Observer<Object>, LifecycleObserver {

    private FragmentActivity mContext;

    public EventAsyncOberseve(FragmentActivity context) {
        this.mContext = context;
        context.getLifecycle().addObserver(this);
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

    public static void boradcastOberseve(EventType eventType) {
        LiveBus.getInstance().setValue(eventType);
    }

    public static void postBcastOberseve(EventType eventType) {
        LiveBus.getInstance().postValue(eventType);
    }

    private boolean emptyCallBack() {
        return mListener == null;
    }

}
