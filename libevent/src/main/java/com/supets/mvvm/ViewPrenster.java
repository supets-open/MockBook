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
public abstract class ViewPrenster<T extends DataBindView>  {

    protected T mView;

    public ViewPrenster(T view) {
        this.mView = view;
        DataBindUtils.injectComponentNo(this);
    }



}
