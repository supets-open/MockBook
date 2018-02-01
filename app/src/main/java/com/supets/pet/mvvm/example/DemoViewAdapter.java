package com.supets.pet.mvvm.example;

import android.util.Log;
import android.view.View;

import com.supets.pet.mockui.R;
import com.supets.pet.mvvm.ViewAdapter;

/**
 * UI处理
 */
public class DemoViewAdapter extends ViewAdapter<DemoView> implements View.OnClickListener {

    public DemoViewAdapter(DemoView view) {
        super(view);
    }

    @Override
    public void init() {
        mView.view(DemoViewId.liveid).setOnClickListener(this);
    }

    public void updateName(String name) {
        mView.text(DemoViewId.liveid, name);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == DemoViewId.liveid) {
            mView.mPrenster.requestUserName();
        }
    }

    public interface DemoViewId {
        int liveid = R.id.live;
    }


}
