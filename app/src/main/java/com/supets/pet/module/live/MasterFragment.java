package com.supets.pet.module.live;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.supets.pet.mvvm.share.SharedViewModel;

public class MasterFragment extends Fragment {


    private SharedViewModel model;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        //test
        model.select("选择");
    }
}