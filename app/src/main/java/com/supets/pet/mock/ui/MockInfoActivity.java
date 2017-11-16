package com.supets.pet.mock.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.supets.commons.widget.CommonHeader;
import com.supets.lib.mail.MailUtil;
import com.supets.pet.mock.Utils;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.config.MockConfig;
import com.supets.pet.mock.core.FormatLogProcess;
import com.supets.pet.mock.dao.EmailDataDB;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mockui.R;

import java.util.List;

public class MockInfoActivity extends Activity {


    private MockData mockData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_info);

        initView();

        String id = getIntent().getStringExtra("id");

        List<MockData> datas = MockDataDB.queryMockDataById(id);
        if (datas != null && datas.size() > 0) {
            mockData = datas.get(0);
            EditText mEditText = (EditText) findViewById(R.id.list);
            mEditText.setText(FormatLogProcess.format(mockData.getData()));

            TextView name = (TextView) findViewById(R.id.name);
            name.setText(FormatLogProcess.format(mockData.getUrl()));

            TextView param = (TextView) findViewById(R.id.param);
            param.setText(Utils.formatParam(mockData.getRequestParam()));

        }

    }


    private void initView() {
        CommonHeader header = (CommonHeader) findViewById(R.id.header);
        header.getTitleTextView().setText("接口数据详情");
        header.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        header.getRightButton().setText("邮件分享");
        header.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSendEmail();
            }
        });
        findViewById(R.id.formatjson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockInfoActivity.this, MockToolActivity.class);
                intent.putExtra("json", mockData.getData());
                startActivity(intent);
            }
        });


    }


    private void doSendEmail() {
        String[] list = EmailDataDB.getEmailList();

        final String[] finalList = list;
        new AlertDialog.Builder(this)
                .setTitle(R.string.emaillist)
                .setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MailUtil.setPort(25);
                        MailUtil.setServer("smtp.163.com");
                        MailUtil.setFrom("疯狂桔子安卓团队");
                        MailUtil.setUser(MockConfig.getEmailName());
                        MailUtil.setPassword(MockConfig.getEmailPass());
                        MailUtil.sendEmail(finalList[which],
                                getString(R.string.crash_title),
                                mockData.getUrl() + "\r\n" + mockData.getRequestParam() + "\r\n" +
                                        mockData.getData());
                    }
                }).show();
    }


}
