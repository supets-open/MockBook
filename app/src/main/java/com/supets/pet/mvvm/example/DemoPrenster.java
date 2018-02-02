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

public class DemoPrenster extends ViewPrenster<DemoView> implements Observer<Object> {

    @ComponentNo
    public DemoViewModel mViewModel;

    @ComponentNo
    public DemoDataRepository dataRepository;


    public DemoPrenster(DemoView view) {
        super(view);
    }

    @Override
    protected void init() {

//        Transformations.map(mViewModel.getUsers(), new Function<List<String>, String>() {
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
//
//        mViewModel.names.observe(mView.getOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String strings) {
//                Log.v("二次更新", strings);
//            }
//        });
//
//        mViewModel.data2.observe(mView.getOwner(), new Observer<List<String>>() {
//            @Override
//            public void onChanged(@Nullable List<String> strings) {
//                Log.v("三次更新", strings.toString());
//            }
//        });
//
//        mViewModel.data3.observe(mView.getOwner(), new Observer<List<String>>() {
//            @Override
//            public void onChanged(@Nullable List<String> strings) {
//                Log.v("四次更新", strings.toString());
//            }
//        });
    }

    public void requestUserName() {

        ArrayList<String> data = dataRepository.getData();
        mViewModel.updateData(data);

//        SharedViewModel model = ViewModelProviders.of(mView.getOwner()).get(SharedViewModel.class);
//        model.select("niha");

        //所有都转发
        //LiveBus.getInstance().observeForever( this);
        //只发对应的owner
        LiveBus.getInstance().observe(mView.getOwner(), this);
        LiveBus.getInstance().setValue("hah");

    }

    public void startGo() {
        mView.getContext().startActivity(new Intent(mView.getContext(), TestLiveCycleActivity.class));
    }



    @Override
    public void onDestory() {
        super.onDestory();
        //移除事件
        LiveBus.getInstance().removeObserver(this);
        LiveBus.getInstance().removeObservers(mView.getOwner());
    }

    @Override
    public void onChanged(@Nullable Object o) {
        Log.v("rxbus",o.toString()+"rxbus"+this);

        //移除事件
        LiveBus.getInstance().removeObserver(this);
        LiveBus.getInstance().removeObservers(mView.getOwner());
    }
}
