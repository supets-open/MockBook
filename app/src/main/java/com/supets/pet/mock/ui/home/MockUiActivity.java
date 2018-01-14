package com.supets.pet.mock.ui.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.ViewInjector;
import com.supets.pet.mockui.R;
import com.zhy.ioc.Bind;


public class MockUiActivity extends TabLayoutBottomActivity {

    @Bind(R.id.header)
    CommonHeader header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjector.injectView(this);
        initView();
    }

    private void initView() {
        header.getTitleTextView().setText(R.string.app_name);
        header.getTitleTextView().setTextColor(ContextCompat.getColor(this,R.color.white));
        header.getWholeView().setBackgroundResource(R.color.appcolor);
        header.getLeftButton().setVisibility(View.GONE);
    }

}
