package com.supets.pet.mock.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import com.supets.commons.widget.CommonHeader;
import com.supets.lib.supetsrouter.uinav.UINav;
import com.supets.pet.mockui.R;

/**
 * supets_shopmail
 *
 * @user lihongjiang
 * @description
 * @date 2017/6/22
 * @updatetime 2017/6/22
 */

public class MockUrlRuleActivity extends Activity {


    private EditText mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_url);

        CommonHeader mHeader = (CommonHeader) findViewById(R.id.header);
        mHeader.getTitleTextView().setText("映射测试");
        mHeader.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mWebView = (EditText) findViewById(R.id.webview);
        mWebView.setText("supets://main");

        findViewById(R.id.testgo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(mWebView.getText().toString()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
