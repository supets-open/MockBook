package com.supets.pet.mvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;

/**
 *   模块解耦:事件管理者
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */

public class ViewModelLogicPrenster<T extends ViewModel> implements LifecycleObserver{

    public T viewModel;

    public ViewModelLogicPrenster(T viewModel) {
        this.viewModel = viewModel;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {}

    public void onPause() {}

    public void onDestory() {}

    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

    public void onCreate(Intent bundle) {}
}
