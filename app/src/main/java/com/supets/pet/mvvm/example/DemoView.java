package com.supets.pet.mvvm.example;

import android.app.Activity;

import com.supets.mvvm.di.Component;
import com.supets.mvvm.di.ComponentLayout;
import com.supets.mvvm.di.InjectComponetUtils;
import com.supets.mvvm.view.ActivityView;
import com.supets.pet.mockui.R;

import lombok.Getter;

/**
 * 组件定义
 */

@ComponentLayout(R.layout.activity_live_test)
public class DemoView extends ActivityView {

    @Component()
    @Getter
    private DemoViewDelegate mDelegate;

    @Component()
    @Getter
    private DemoPrenster mPrenster;

    public DemoView(Activity mContext) {
        super(mContext);
        InjectComponetUtils.injectComponentNo(this);
        InjectComponetUtils.injectComponent(this, DemoView.class, this);
    }

}
