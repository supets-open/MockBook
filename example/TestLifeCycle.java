package com.supets.pet.mvvm.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.supets.mvvm.Component;
import com.supets.mvvm.DataBindUtils;


public class TestLifeCycle extends FragmentActivity {

    @Component()
    private DemoView mRootView;

    @Component()
    public DemoPrenster mPrenster;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindUtils.injectComponent(this, this);
        DataBindUtils.injectComponentNo(this);
        getLifecycle().addObserver(mPrenster);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPrenster.onActivityResult(requestCode, resultCode, data);
    }


}
