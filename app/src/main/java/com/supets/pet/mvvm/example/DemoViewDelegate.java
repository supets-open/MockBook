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
        init();
    }

    public void init() {
        mView.view(DemoViewId.liveid).setOnClickListener(this);
        mView.view(DemoViewId.livemodel).setOnClickListener(this);
    }

    public void updateName(String name) {
        mView.text(DemoViewId.liveid, name);
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
        mView.mPrenster.requestUserName();
    }

    public void startGo() {
        mView.mPrenster.startGo();
    }

    public interface DemoViewId {
        int liveid = R.id.live;
        int livemodel = R.id.livemodel;
    }

}
