package com.supets.mvvm.view;

import android.content.Context;
import android.support.annotation.Keep;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supets.mvvm.di.InjectComponetUtils;


/**
 * 视图模块
 */
@Keep
public class ViewGroupSubView extends BaseView<Context> {

    private View mRootView;

    public ViewGroupSubView(ViewGroup mContext) {
        super(mContext.getContext());
        int xml = InjectComponetUtils.injectViewGroupUI(this);
        this.mRootView = LayoutInflater.from(mContext.getContext()).inflate(xml, mContext, false);
    }
    public View getRootView() {
        return mRootView;
    }

}