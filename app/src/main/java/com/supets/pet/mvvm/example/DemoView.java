package com.supets.pet.mvvm.example;

import android.app.Activity;

import com.supets.pet.mockui.R;
import com.supets.pet.mvvm.Component;
import com.supets.pet.mvvm.ViewModelLayout;
import com.supets.pet.mvvm.ViewProxy;

@ViewModelLayout(R.layout.activity_live_test)
public class DemoView extends ViewProxy {

    public DemoView(Activity mContext) {
        super(mContext);
    }

    @Component()
    public DemoViewAdapter mAdapter;

    @Component()
    public DemoPrenster mPrenster;

    public interface DemoViewId {
        int liveid = R.id.live;
    }

}
