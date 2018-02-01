package com.supets.pet.mvvm;

import android.support.annotation.Keep;
import android.support.annotation.StringRes;

/**
 * 视图适配器
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */
@Keep
public abstract class ViewAdapter<T extends ViewProxy> {

    protected T mView;

    public ViewAdapter(T view) {
        this.mView = view;
        init();
    }

    public abstract void init();

    public String string(@StringRes int id) {
        return mView.getContext().getString(id);
    }

    public String string(@StringRes int id, Object... args) {
        return mView.getContext().getString(id, args);
    }
}
