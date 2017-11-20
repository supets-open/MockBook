package com.supets.pet;

import android.app.Application;

import com.supets.pet.crash.DefaultBugHandler;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DefaultBugHandler.getInstance().init(this);
        OkHttpUtils.initClient(new OkHttpClient());
    }
}
