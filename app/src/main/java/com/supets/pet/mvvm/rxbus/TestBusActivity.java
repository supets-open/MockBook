package com.supets.pet.mvvm.rxbus;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.leakcanary.RefWatcher;
import com.supets.libevent.EventAsyncOberseve;
import com.supets.libevent.EventCallBackListener;
import com.supets.libevent.EventType;
import com.supets.libevent.LiveBus;
import com.supets.pet.MyApplication;
import com.supets.pet.mockui.R;
import com.supets.pet.module.live.TestLiveCycleActivity;
import com.supets.pet.mvvm.share.TestSharedViewModel;

public class TestBusActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_bus_test);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        TestSharedViewModel model = ViewModelProviders.of(this).get(TestSharedViewModel.class);
        model.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.v("共享数据接受" + this, s);
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn0) {
            new EventAsyncOberseve(this).registerAsyncForever(new EventCallBackListener() {
                @Override
                public boolean callBack(EventType event) {

                    Toast.makeText(getApplicationContext(), event.eventName, Toast.LENGTH_SHORT).show();
                    return event.eventType == 1;
                }
            });
            startActivity(new Intent(this, TestLiveCycleActivity.class));
        }

        if (v.getId() == R.id.btn1) {
            new EventAsyncOberseve(this).registerAsync(new EventCallBackListener() {
                @Override
                public boolean callBack(EventType event) {
                    Toast.makeText(getApplicationContext(), event.eventName + "-share2", Toast.LENGTH_SHORT).show();
                    return event.eventType == 1;
                }
            });
            startActivity(new Intent(this, TestLiveCycleActivity.class));
        }


        if (v.getId() == R.id.btn2) {
            EventType eventType = new EventType();
            eventType.eventName = "登陆成功";
            eventType.eventType = 2;
            EventAsyncOberseve.boradcastOberseve(eventType);
        }

        if (v.getId() == R.id.btn3) {
            TestSharedViewModel model = ViewModelProviders.of(this).get(TestSharedViewModel.class);
            model.select("发送共享数据");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);//1
        refWatcher.watch(this);
    }

}
