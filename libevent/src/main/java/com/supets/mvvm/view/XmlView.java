package com.supets.mvvm.view;

import android.content.Context;
import android.support.annotation.Keep;
import android.view.LayoutInflater;
import android.view.View;

import com.supets.mvvm.di.InjectComponetUtils;


/**
 * 视图模块
 */
@Keep
public class XmlView extends BaseView<Context> {

    private View mRootView;

    public XmlView(Context mContext) {
        super(mContext);
        int xml = InjectComponetUtils.injectViewGroupUI(this);
        this.mRootView = LayoutInflater.from(mContext).inflate(xml, null);
    }

    public View getRootView() {
        return mRootView;
    }
}