package com.supets.pet.mvvm.example;

import android.view.View;

import com.supets.mvvm.DataBindAdapter;
import com.supets.pet.mockui.R;

/**
 * UI处理
 */
public class DemoViewDelegate extends DataBindAdapter<DemoView> implements View.OnClickListener {

    public DemoViewDelegate(DemoView view) {
        super(view);
    }

    public void init() {
        getView().view(DemoViewId.liveid).setOnClickListener(this);
        getView().view(DemoViewId.livemodel).setOnClickListener(this);
    }

    public void updateName(String name) {
        getView().text(DemoViewId.liveid, name);
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
