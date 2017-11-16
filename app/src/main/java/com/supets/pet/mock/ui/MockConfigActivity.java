package com.supets.pet.mock.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.config.MockConfig;
import com.supets.pet.mockui.R;

public class MockConfigActivity extends Activity implements View.OnClickListener {

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
        mDebugSwitch.setChecked(MockConfig.getDebugMode());

        mDebugMore = (CheckBox) findViewById(R.id.jsonswitch);
        mDebugMore.setOnClickListener(this);
        mDebugMore.setChecked(MockConfig.getJsonSwitch());

        mApi = (EditText) findViewById(R.id.api);
        mApi.setText(MockConfig.getBaseAPI());

        View apisave = findViewById(R.id.apisave);
        apisave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mApi.getText().toString())) {
                    MockConfig.setBaseAPI(mApi.getText().toString().trim());
                    Toast.makeText(MockConfigActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                }

            }
        });

        final EditText emailname = (EditText) findViewById(R.id.emailname);
        emailname.setText(MockConfig.getEmailName());

        View emailnamesave = findViewById(R.id.emailnamesave);
        emailnamesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(emailname.getText().toString())) {
                    MockConfig.setEmailName(emailname.getText().toString().trim());
                    Toast.makeText(MockConfigActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                }

            }
        });


        final EditText emailpass = (EditText) findViewById(R.id.emailpass);
        emailpass.setText(MockConfig.getEmailPass());

        View emailpasssave = findViewById(R.id.emailpasssave);
        emailpasssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(emailpass.getText().toString())) {
                    MockConfig.setEmailPass(emailpass.getText().toString().trim());
                    Toast.makeText(MockConfigActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == mDebugSwitch) {
            MockConfig.setDebugMode(mDebugSwitch.isChecked());
        }

        if (v == mDebugMore) {
            MockConfig.setJsonSwitch(mDebugMore.isChecked());
        }
    }
}
