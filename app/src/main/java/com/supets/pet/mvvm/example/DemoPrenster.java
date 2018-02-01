package com.supets.pet.mvvm.example;

import android.arch.core.util.Function;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import android.util.Log;

import com.supets.pet.mvvm.ComponentNo;
import com.supets.pet.mvvm.ViewPrenster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 模块解耦和事件管理者
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */

public class DemoPrenster extends ViewPrenster<DemoView> implements LifecycleObserver {

    @ComponentNo
    public DemoViewModel mViewModel;

    @ComponentNo
    public DemoDataRepository dataRepository;


    public DemoPrenster(DemoView view) {
        super(view);
    }

    @Override
    protected void init() {

        Transformations.map(mViewModel.getUsers(), new Function<List<String>, String>() {
            @Override
            public String apply(List<String> input) {
                return input.toString();
            }
        }).observe(mView.getOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String strings) {
                mView.mAdapter.updateName(strings);
                Log.v("一次更新", strings);
            }
        });

        mViewModel.names.observe(mView.getOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String strings) {
                Log.v("二次更新", strings);
            }
        });

        mViewModel.data2.observe(mView.getOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                Log.v("三次更新", strings.toString());
            }
        });

        mViewModel.data3.observe(mView.getOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                Log.v("四次更新", strings.toString());
            }
        });
    }


    public void onCreate() {
        super.onCreate();
        Log.v("DemoPrenster", "onCreate");
    }


    public void requestUserName() {

        ArrayList<String> data = dataRepository.getData();
        mViewModel.updateData(data);
    }


}
