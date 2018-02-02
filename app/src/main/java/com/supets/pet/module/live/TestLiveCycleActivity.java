package com.supets.pet.module.live;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.supets.pet.mvvm.rxbus.TestBusActivity;

public class TestLiveCycleActivity extends TestBusActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WifiLiveData.getInstance(this).observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(@Nullable Integer integer) {
//
//                Log.v("wifi",integer.intValue()+"");
//
//            }
//        });
//
//        SharedViewModel model = ViewModelProviders.of(this).get(SharedViewModel.class);
//        model.getSelected().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                Log.v("test", "选择2");
//
//            }
//        });

    }


    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_live_test);
//
//        final MYLiveData data = new MYLiveData();
//        data.observe(this, new Observer<String>() {
//            public void onChanged(@Nullable String s) {
//                Log.v("livedata", "ss==" + s);
//            }
//        });
//
//
//        findViewById(R.id.live).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                data.updateData("lhj");
//            }
//        });
//
//        model = ViewModelProviders.of(this).get(SharedViewModel.class);
//
//        findViewById(R.id.livemodel).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //test
//                model.select("选择");
//            }
//        });
//
//        getLifecycle().addObserver(new MYLiveCycleObserver());
//
//
//    }
//
//    private SharedViewModel model;

}
