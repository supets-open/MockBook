package com.supets.pet.mvvm.example;

import android.view.View;

import com.supets.pet.mvvm.ViewAdapter;

public class DemoViewAdapter extends ViewAdapter<DemoView> implements View.OnClickListener {

    public DemoViewAdapter(DemoView view) {
        super(view);
        view.clickEvent(DemoView.DemoViewId.liveid, this);
    }

    public void updateName(String name) {
        mView.text(DemoView.DemoViewId.liveid, name);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == DemoView.DemoViewId.liveid) {
            mView.mPrenster.requestUserName();
        }

    }
}
