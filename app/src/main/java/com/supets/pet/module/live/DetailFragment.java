package com.supets.pet.module.live;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.supets.pet.base.BaseFragment;
import com.supets.pet.mockui.R;
import com.supets.pet.mvvm.share.SharedViewModel;

public class DetailFragment extends BaseFragment {

    private  TextView textView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_live_fragment_test;
    }


    @Override
    public void findViews(View view) {
        textView = view.findViewById(R.id.text);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {

    }

}