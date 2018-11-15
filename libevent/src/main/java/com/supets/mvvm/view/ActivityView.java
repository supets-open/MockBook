package com.supets.mvvm.view;

import android.app.Activity;
import android.support.annotation.Keep;
import android.view.View;

import com.supets.mvvm.di.InjectComponetUtils;


/**
 * 视图模块
 */
@Keep
public class ActivityView extends BaseView<Activity> {

    private View mRootView;

    public ActivityView(Activity mContext) {
        super(mContext);
        int xml = InjectComponetUtils.injectViewGroupUI(this);
        mContext.setContentView(xml);
        this.mRootView = mContext.getWindow().getDecorView();
    }

    public View getRootView() {
        return mRootView;
    }
}