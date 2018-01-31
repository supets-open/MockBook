package com.supets.pet.mvvm;

import android.support.annotation.StringRes;

/**
 * 视图适配器
 *
 * @user lihongjiang
 * @description
 * @date 2017/5/19
 * @updatetime 2017/5/19
 */

public class ViewModelAdapter<T extends ViewModel> {

    public T viewModel;

    public ViewModelAdapter(T viewModel) {
        this.viewModel = viewModel;
    }

    public String string(@StringRes int id) {
        return viewModel.getContext().getString(id);
    }

    public String string(@StringRes int id, Object... args) {
        return viewModel.getContext().getString(id, args);
    }
}
