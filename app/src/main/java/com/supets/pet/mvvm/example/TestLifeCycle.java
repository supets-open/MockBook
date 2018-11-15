package com.supets.pet.mvvm.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.supets.mvvm.di.Component;
import com.supets.mvvm.di.InjectComponetUtils;

import lombok.Getter;


public class TestLifeCycle extends FragmentActivity {

    @Component()
    @Getter
    private DemoView mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectComponetUtils.injectComponent(this, Activity.class, this);
        getLifecycle().addObserver(getMRootView().getMPrenster());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getMRootView().getMPrenster().onActivityResult(requestCode, resultCode, data);
    }


}
