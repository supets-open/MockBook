package com.supets.pet.mvvm.share;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;


/**
 *  只有相同生命周期管理共享
 */
public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<>();

    public void select(String item) {
        selected.setValue(item);
    }

    public LiveData<String> getSelected() {
        return selected;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //Log.v("SharedViewModel", "不同页面会销毁");
    }
}