package com.supets.pet.mvvm.example;

import android.app.Activity;

import com.supets.pet.mockui.R;
import com.supets.pet.mvvm.Component;
import com.supets.pet.mvvm.ComponentNo;
import com.supets.pet.mvvm.ComponentLayout;
import com.supets.pet.mvvm.DataBindView;

/**
 * 组件定义
 */

@ComponentLayout(R.layout.activity_live_test)
public class DemoView extends DataBindView  {

    public DemoView(Activity mContext) {
        super(mContext);
    }

    @Component()
    public DemoViewAdapter mAdapter;

    @Component()
    public DemoPrenster mPrenster;

}
