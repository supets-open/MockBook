package com.supets.pet.mvvm.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.supets.pet.mvvm.rxbus.LiveBus;

public class TestLifeCycle extends FragmentActivity {

    private DemoView mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean  isob=LiveBus.getInstance().hasActiveObservers();
        boolean  isob2=LiveBus.getInstance().hasObservers();
        Log.v("hasActiveObservers",isob+"");
        Log.v("hasObservers",isob2+"");

        mRootView = new DemoView(this);
        getLifecycle().addObserver(mRootView.mPrenster);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mRootView.mPrenster.onActivityResult(requestCode, resultCode, data);
    }


}
