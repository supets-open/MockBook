package com.supets.share;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


/**
 * 只有相同生命周期管理共享
 */
public abstract class SharedViewModel<T> extends ViewModel {

    private final MutableLiveData<T> data = new MutableLiveData<>();

    public void select(T item) {
        data.setValue(item);
    }

    public LiveData<T> getData() {
        return data;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //Log.v("SharedViewModel", "不同页面会销毁");
    }
}