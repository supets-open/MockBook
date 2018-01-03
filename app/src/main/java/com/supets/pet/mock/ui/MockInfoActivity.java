package com.supets.pet.mock.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.supets.commons.widget.CommonHeader;
import com.supets.lib.mail.MailUtil;
import com.supets.pet.mock.utils.Utils;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.config.Config;
import com.supets.pet.mock.utils.FormatLogProcess;
import com.supets.pet.mock.dao.EmailDataDB;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mockui.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class MockInfoActivity extends Activity {


    private MockData mockData;
    private TextView mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_info);

        initView();

        String id = getIntent().getStringExtra("id");

        List<MockData> datas = MockDataDB.queryMockDataById(id);
        if (datas != null && datas.size() > 0) {
            mockData = datas.get(0);
            mEditText = (TextView) findViewById(R.id.list);
            mEditText.setText(FormatLogProcess.format(mockData.getData()));

            TextView name = (TextView) findViewById(R.id.name);
            name.setText(FormatLogProcess.format(mockData.getUrl()));

            TextView param = (TextView) findViewById(R.id.param);
            param.setText(Utils.formatParam(mockData.getRequestParam()));

        }


        findViewById(R.id.sencondTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpUtils.post().url(mockData.getUrl())
                        .params(Utils.formatHashMap(mockData.getRequestParam())).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mEditText.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mEditText.setText(response);
                    }
                });

            }
        });

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
                        MailUtil.setUser(Config.getEmailName());
                        MailUtil.setPassword(Config.getEmailPass());
                        MailUtil.sendEmail(finalList[which],
                                getString(R.string.crash_title),
                                new StringBuffer().append("接口名称：")
                                        .append("\r\n")
                                        .append(mockData.getUrl())
                                        .append("\r\n")
                                        .append("请求参数：")
                                        .append("\r\n")
                                        .append(Utils.formatParam(mockData.getRequestParam()))
                                        .append("\r\n")
                                        .append("请求结果：")
                                        .append("\r\n")
                                        .append(FormatLogProcess.format(mockData.getData()))
                                        .toString()
                        );
                    }
                }).show();
    }


}
