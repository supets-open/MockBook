package com.supets.pet.mock.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.supets.pet.service.PullMsgService;

public class PullMsgActivity extends Activity {

    public void startMsgService() {
        Intent intent = new Intent(this, PullMsgService.class);
        startService(intent);
    }

    public void stopMsgService() {
        Intent intent = new Intent(this, PullMsgService.class);
        stopService(intent);
    }

    public void bindMsgService() {
        Intent intent = new Intent(this, PullMsgService.class);
        bindService(intent, msgconnect, Context.BIND_AUTO_CREATE);
    }

    public void unbindMsgService() {
        unbindService(msgconnect);
    }

    private ServiceConnection msgconnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
