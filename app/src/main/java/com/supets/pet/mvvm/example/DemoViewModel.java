package com.supets.pet.mvvm.example;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * UI和数据的桥梁
 */
public class DemoViewModel extends ViewModel {

    public MutableLiveData<List<String>> users = new MutableLiveData<>();

    public final LiveData<String> names = Transformations.switchMap(users, new Function<List<String>, LiveData<String>>() {
        @Override
        public LiveData<String> apply(List<String> input) {
            MediatorLiveData<String> data = new MediatorLiveData<>();
            data.postValue(input.toString());
            return data;
        }
    });


    public LiveData<List<String>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    public MutableLiveData<List<String>> data2 = new MutableLiveData<>();
    public MutableLiveData<List<String>> data3 = new MutableLiveData<>();


    private void loadUsers() {
    }

    public void updateData(ArrayList<String> list) {
        users.postValue(list);
        data2.setValue(list);
        data3.setValue(list);
    }

}
