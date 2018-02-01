package com.supets.pet.mvvm.example;

import android.arch.core.util.Function;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import android.util.Log;

import com.supets.pet.mvvm.ViewPrenster;

import java.util.List;


/**
 * 模块解耦:事件管理者
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */

public class DemoPrenster extends ViewPrenster<DemoView,DemoViewModel> implements LifecycleObserver {

    public DemoPrenster(DemoView view) {
        super(view,new DemoViewModel());
        Transformations.map(mViewModel.getUsers(), new Function<List<String>, String>() {
            @Override
            public String apply(List<String> input) {
                return input.toString();
            }
        }).observe(mView.getOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String strings) {
                mView.mAdapter.updateName(strings);
            }
        });
    }

    public void onCreate() {
        super.onCreate();
        Log.v("DemoPrenster", "onCreate");
    }


    public void requestUserName() {
        mViewModel.requestName();
    }


}
