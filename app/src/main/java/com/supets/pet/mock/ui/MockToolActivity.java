package com.supets.pet.mock.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.supets.commons.widget.CommonHeader;
import com.supets.lib.mail.MailUtil;
import com.supets.pet.jsonview.JSONEditView;
import com.supets.pet.mock.config.MockConfig;
import com.supets.pet.mock.db.EmailDataDB;
import com.supets.pet.mockui.R;

/**
 * supets_shopmail
 *
 * @user lihongjiang
 * @description
 * @date 2017/6/21
 * @updatetime 2017/6/21
 */

public class MockToolActivity extends Activity {


    private CommonHeader mCommonHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mocktool_jsonedit);

        mCommonHeader = (CommonHeader) findViewById(R.id.header);
        mCommonHeader.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mCommonHeader.getTitleTextView().setText(R.string.jsonedit);

        JSONEditView jsonEditView = (JSONEditView) findViewById(R.id.jsonEditView);
        String json = getIntent().getStringExtra("json");
        jsonEditView.formatJson(json);

        if (TextUtils.isEmpty(json)){
            return;
        }

        mCommonHeader.getRightButton().setText("邮件分享");
        mCommonHeader.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSendEmail();
            }
        });
    }


    private void doSendEmail() {
        String[] list =EmailDataDB.getEmailList();

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
                                getIntent().getStringExtra("json"));
                    }
                }).show();
    }
}
