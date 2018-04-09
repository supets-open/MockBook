package com.supets.pet.mvvm;

import android.support.annotation.Keep;
import android.support.annotation.StringRes;

@Keep
public abstract class DataBindAdapter<T extends DataBindView> {

    protected T mView;

    public DataBindAdapter(T view) {
        this.mView = view;
    }

    public String string(@StringRes int id) {
        return mView.getContext().getString(id);
    }

    public String string(@StringRes int id, Object... args) {
        return mView.getContext().getString(id, args);
    }
}
