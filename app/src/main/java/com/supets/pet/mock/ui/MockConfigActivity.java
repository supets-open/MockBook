package com.supets.pet.mock.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.config.Config;
import com.supets.pet.mockui.R;

public class MockConfigActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox mDebugSwitch;
    private CheckBox mDebugMore;

    private EditText mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_config);
        initView();
    }

    private void initView() {

        CommonHeader header = (CommonHeader) findViewById(R.id.header);
        header.getTitleTextView().setText("测试配置");

        header.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDebugSwitch = (CheckBox) findViewById(R.id.debugmode);
        mDebugSwitch.setOnClickListener(this);
        mDebugSwitch.setChecked(Config.getDebugMode());

        mDebugMore = (CheckBox) findViewById(R.id.jsonswitch);
        mDebugMore.setOnClickListener(this);
        mDebugMore.setChecked(Config.getJsonSwitch());

        mApi = (EditText) findViewById(R.id.api);
        mApi.setText(Config.getBaseAPI());

        View apisave = findViewById(R.id.apisave);
        apisave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mApi.getText().toString())) {
                    Config.setBaseAPI(mApi.getText().toString().trim());
                    Toast.makeText(MockConfigActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final EditText emailname = (EditText) findViewById(R.id.emailname);
        emailname.setText(Config.getEmailName());

        View emailnamesave = findViewById(R.id.emailnamesave);
        emailnamesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(emailname.getText().toString())) {
                    Config.setEmailName(emailname.getText().toString().trim());
                    Toast.makeText(MockConfigActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }

            }
        });


        final EditText emailpass = (EditText) findViewById(R.id.emailpass);
        emailpass.setText(Config.getEmailPass());

        View emailpasssave = findViewById(R.id.emailpasssave);
        emailpasssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(emailpass.getText().toString())) {
                    Config.setEmailPass(emailpass.getText().toString().trim());
                    Toast.makeText(MockConfigActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == mDebugSwitch) {
            Config.setDebugMode(mDebugSwitch.isChecked());
        }

        if (v == mDebugMore) {
            Config.setJsonSwitch(mDebugMore.isChecked());
        }
    }
}
