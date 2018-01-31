package com.supets.pet.module.live;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {


    private MutableLiveData<List<String>> users;

    public LiveData<List<String>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // do async operation to fetch users
    }

    @Override
    protected void onCleared() {
        //资源清理
    }
}