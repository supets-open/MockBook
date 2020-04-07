package com.supets.pet.mvvm.example;

import android.app.Activity;

import com.supets.mvvm.Component;
import com.supets.mvvm.ComponentLayout;
import com.supets.mvvm.DataBindView;
import com.supets.pet.mockui.R;

/**
 * 组件定义
 */

@ComponentLayout(R.layout.activity_live_test)
public class DemoView extends DataBindView  {

    public DemoView(Activity mContext) {
        super(mContext);
    }

    @Component()
    private DemoViewDelegate mDelegate;

    public DemoViewDelegate getDelegate() {
        return mDelegate;
    }
}
