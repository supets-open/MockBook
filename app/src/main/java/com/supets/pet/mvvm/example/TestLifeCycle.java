package com.supets.pet.mvvm.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.supets.pet.mockui.R;

public class TestLifeCycle extends FragmentActivity {

    private DemoView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = (DemoView) new DemoView().of(this, R.layout.activity_live_test);
        setContentView(view.rootView());
        getLifecycle().addObserver(view.prenster);
    }
}
