package com.supets.pet.mvvm.example;

import android.content.Intent;

import com.supets.pet.mvvm.ViewModelLogicPrenster;

/**
 * 模块解耦:事件管理者
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */

public class DemoLogicPrenster extends ViewModelLogicPrenster<DemoView> {


    public DemoLogicPrenster(DemoView viewModel) {
        super(viewModel);
    }

    @Override
    public void onCreate(Intent bundle) {
        viewModel.text(DemoView.DemoViewId.liveid,"更新");
    }

}
