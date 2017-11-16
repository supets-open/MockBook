package com.supets.pet.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.config.MockConfig;
import com.supets.pet.mock.dao.MockDataDB;

import java.util.Date;

public class NetWorkInterceptionService extends BroadcastReceiver {

    public static final String MOCK_SERVICE_NETWORK = "mock.crash.network";

    @Override
    public void onReceive(Context context, Intent intent) {


        if (MOCK_SERVICE_NETWORK.equals(intent.getAction())) {

            if (MockConfig.isFilterGuice(intent.getStringExtra("url"))
                    && MockConfig.getJsonSwitch()
                    && !MockConfig.getDebugMode()) {
                try {
                    MockData data = new MockData();
                    data.setUrl(intent.getStringExtra("url"));
                    data.setData(intent.getStringExtra("message"));
                    data.setRequestParam(intent.getStringExtra("requestParam"));
                    data.setTime(new Date());
                    MockDataDB.insertMockData(data);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
