package com.supets.pet.mvvm.example;

import android.arch.core.util.Function;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.supets.pet.module.live.TestLiveCycleActivity;
import com.supets.pet.mvvm.ComponentNo;
import com.supets.pet.mvvm.ViewPrenster;
import com.supets.pet.mvvm.rxbus.LiveBus;
import com.supets.pet.mvvm.share.SharedViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * 模块解耦和事件管理者
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */

public class DemoPrenster extends ViewPrenster<DemoView> {

    @ComponentNo()
    public DemoViewModel mDemoViewModel;

    @ComponentNo
    public DemoDataRepository dataRepository;

    public DemoPrenster(DemoView view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        mView.mAdapter.init();
    }

    public void init() {

//        SharedViewModel model = ViewModelProviders.of(mView.getOwner()).get(SharedViewModel.class);
//        model.getSelected().observe(mView.getOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                //更新数据
//                mDemoViewModel.updateData(s);
//            }
//        });


//        Transformations.map(mDemoViewModel.getUsers(), new Function<List<String>, String>() {
//            @Override
//            public String apply(List<String> input) {
//                return input.toString();
//            }
//        }).observe(mView.getOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String strings) {
//                mView.mAdapter.updateName(strings);
//                Log.v("一次更新", strings);
//            }
//        });

        mDemoViewModel.users.observe(mView.getOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mView.mAdapter.updateName(s);
            }
        });

    }

    public void requestUserName() {
        //获取网络数据
        String data = dataRepository.getData();
        //更新数据
        mDemoViewModel.updateData(data);

        SharedViewModel model = ViewModelProviders.of(mView.getOwner()).get(SharedViewModel.class);
        model.select("niha");

    }

    public void startGo() {
        mView.getContext().startActivity(new Intent(mView.getContext(), TestLiveCycleActivity.class));
    }

}
