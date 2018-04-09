package com.supets.pet.mvvm.example;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * UI和数据的桥梁
 */
public class DemoViewModel extends ViewModel {

    public MutableLiveData<String> users = new MutableLiveData<>();

//    public final LiveData<String> names = Transformations.switchMap(users, new Function<List<String>, LiveData<String>>() {
//        @Override
//        public LiveData<String> apply(List<String> input) {
//            MediatorLiveData<String> data = new MediatorLiveData<>();
//            data.postValue(input.toString());
//            return data;
//        }
//    });

    public void updateData(String list) {
        users.setValue(list);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
