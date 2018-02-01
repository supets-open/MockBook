package com.supets.pet.mvvm.example;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * UI和数据的桥梁
 */
public class DemoViewModel extends ViewModel {

    private MutableLiveData<List<String>> users;

    public LiveData<List<String>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        ArrayList<String> list = new ArrayList<>();
        list.add("初始化");
        list.add("初始化");
        list.add("初始化");
        list.add("初始化");
        users.postValue(list);
    }

    public void requestName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("老化角质");
        list.add("老化角质");
        list.add("老化角质");
        list.add("老化角质");
        users.postValue(list);
        Log.v("DemoViewAdapter", "requestName");
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
