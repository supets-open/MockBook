package com.supets.pet.mvvm.rxbus;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.supets.pet.mockui.R;
import com.supets.pet.module.live.TestLiveCycleActivity;
import com.supets.pet.mvvm.share.SharedViewModel;

public class TestBusActivity extends AppCompatActivity implements View.OnClickListener, Observer<Object> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_bus_test);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        SharedViewModel model = ViewModelProviders.of(this).get(SharedViewModel.class);
        model.getSelected().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.v("共享数据接受" + this, s);
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn0) {
            LiveBus.getInstance().observeForever(this);
            startActivity(new Intent(this, TestLiveCycleActivity.class));
        }

        if (v.getId() == R.id.btn1) {
            LiveBus.getInstance().observe(this, this);
            startActivity(new Intent(this, TestLiveCycleActivity.class));
        }
        if (v.getId() == R.id.btn2) {
            LiveBus.getInstance().setValue("registerlogin");
        }

        if (v.getId() == R.id.btn3) {
            SharedViewModel model = ViewModelProviders.of(this).get(SharedViewModel.class);
            model.select("发送共享数据");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除事件
        LiveBus.getInstance().removeObserver(this);
        LiveBus.getInstance().removeObservers(this);
    }

    @Override
    public void onChanged(@Nullable Object o) {
        Log.v("注册接受" + this, o.toString());
        LiveBus.getInstance().removeObserver(this);
        LiveBus.getInstance().removeObservers(this);
    }
}
