package com.supets.pet.mvvm.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public class TestLifeCycle extends FragmentActivity {

    private DemoView mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = new DemoView(this);
        getLifecycle().addObserver(mRootView.mPrenster);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mRootView.mPrenster.onActivityResult(requestCode, resultCode, data);
    }


}
