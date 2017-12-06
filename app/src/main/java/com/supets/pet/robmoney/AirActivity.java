package com.supets.pet.robmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.supets.pet.mockui.R;

public class AirActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qhb);

        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

    public void start(View v) {
        if (AirAccessibilityService.ALL) {
            AirAccessibilityService.ALL = false;
            ((Button) v).setText("对话内监控+关");
        } else {
            AirAccessibilityService.ALL = true;
            ((Button) v).setText("对话内监控+开");
        }

    }
}