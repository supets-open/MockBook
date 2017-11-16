package com.supets.pet;

import android.app.Application;

import com.supets.pet.crash.DefaultBugHandler;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DefaultBugHandler.getInstance().init(this);
    }
}
