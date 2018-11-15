package com.supets.pet.mvvm.example;

import android.view.View;

import com.supets.mvvm.view.ViewDelegate;
import com.supets.pet.mockui.R;

/**
 * UI处理  事件采用接口回调或者eventbus
 */
public class DemoViewDelegate extends ViewDelegate<DemoView> implements View.OnClickListener {

    public DemoViewDelegate(DemoView view) {
        super(view);
    }

    public void init() {
        getView().getRootView().findViewById(DemoViewId.liveid).setOnClickListener(this);
        getView().getRootView().findViewById(DemoViewId.livemodel).setOnClickListener(this);
    }

    public void updateName(String name) {
        ViewDelegate.text(getView().getRootView(), DemoViewId.liveid, name);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == DemoViewId.liveid) {
            requestUserName();
        }
        if (v.getId() == DemoViewId.livemodel) {
            startGo();
        }
    }

    public void requestUserName() {

    }

    public void startGo() {

    }

    public interface DemoViewId {
        int liveid = R.id.live;
        int livemodel = R.id.livemodel;
    }

}
