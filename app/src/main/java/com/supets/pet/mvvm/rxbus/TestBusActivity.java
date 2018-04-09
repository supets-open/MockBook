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

import com.supets.pet.mockui.R;
import com.supets.pet.module.live.TestLiveCycleActivity;
import com.supets.pet.mvvm.share.SharedViewModel;

public class TestBusActivity extends AppCompatActivity implements View.OnClickListener {

    private EventAsyncOberseve loginOberseve;
    private EventAsyncOberseve share;

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

        loginOberseve = new EventAsyncOberseve(this);
        share = new EventAsyncOberseve(this);
        getLifecycle().addObserver(loginOberseve);
        getLifecycle().addObserver(share);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn0) {

            loginOberseve.registerAsyncForever(new EventCallBackListener() {
                @Override
                public boolean callBack(EventType event) {

                    Toast.makeText(getApplicationContext(), event.eventName, Toast.LENGTH_SHORT).show();
                    return event.eventType == 1;
                }
            });
            share.registerAsyncForever(new EventCallBackListener() {
                @Override
                public boolean callBack(EventType event) {
                    Toast.makeText(getApplicationContext(), event.eventName+"-share", Toast.LENGTH_SHORT).show();
                    return event.eventType == 2;
                }
            });
            startActivity(new Intent(this, TestLiveCycleActivity.class));
        }

        if (v.getId() == R.id.btn1) {
            loginOberseve.registerAsync(new EventCallBackListener() {
                @Override
                public boolean callBack(EventType event) {
                    Toast.makeText(getApplicationContext(), event.eventName, Toast.LENGTH_SHORT).show();
                    return event.eventType == 1;
                }
            });
            share.registerAsync(new EventCallBackListener() {
                @Override
                public boolean callBack(EventType event) {
                    Toast.makeText(getApplicationContext(), event.eventName+"-share", Toast.LENGTH_SHORT).show();
                    return event.eventType == 2;
                }
            });
            startActivity(new Intent(this, TestLiveCycleActivity.class));
        }
        if (v.getId() == R.id.btn2) {

            EventType eventType = new EventType();
            eventType.eventName = "登陆成功";
            eventType.eventType = 2;
            loginOberseve.postOberseve(eventType);
        }

        if (v.getId() == R.id.btn3) {
            SharedViewModel model = ViewModelProviders.of(this).get(SharedViewModel.class);
            model.select("发送共享数据");
        }

    }

}
