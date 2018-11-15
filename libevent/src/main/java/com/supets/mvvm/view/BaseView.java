package com.supets.mvvm.view;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class BaseView<T extends Context> implements IView {

    protected BaseView(T context) {
        this.mContext = context;
    }

    private T mContext;

    public Context getContext() {
        return mContext;
    }

    public LifecycleOwner getOwner() {
        if (mContext instanceof LifecycleOwner) {
            return (LifecycleOwner) mContext;
        }
        return null;
    }

    public FragmentActivity getFragmentActivity() {
        if (mContext instanceof FragmentActivity) {
            return (FragmentActivity) mContext;
        }
        return null;
    }

}
