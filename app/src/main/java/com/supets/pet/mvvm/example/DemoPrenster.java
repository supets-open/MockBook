package com.supets.pet.mvvm.example;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.supets.mvvm.ComponentNo;
import com.supets.mvvm.ViewPrenster;
import com.supets.pet.module.live.TestLiveCycleActivity;
import com.supets.pet.mvvm.share.TestSharedViewModel;


/**
 * 模块解耦和事件管理者
 */

public class DemoPrenster extends ViewPrenster<DemoView> {

    @ComponentNo()
    public DemoViewModel mDemoViewModel;

    @ComponentNo
    public DemoDataRepository dataRepository;

    public DemoPrenster(DemoView view) {
        super(view);
        init();
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

        TestSharedViewModel model = ViewModelProviders.of(mView.getOwner()).get(TestSharedViewModel.class);
        model.select("niha");

    }

    public void startGo() {
        mView.getContext().startActivity(new Intent(mView.getContext(), TestLiveCycleActivity.class));
    }


}
