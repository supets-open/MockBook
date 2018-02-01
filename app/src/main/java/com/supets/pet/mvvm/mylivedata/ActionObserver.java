package com.supets.pet.mvvm.mylivedata;

import android.arch.lifecycle.Observer;

public interface ActionObserver<T> extends Observer<T>, ActionHandler {

}