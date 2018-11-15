package com.supets.pet.mvvm.example;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.supets.mvvm.di.ComponentNo;
import com.supets.mvvm.presenter.ViewPrenster;
import com.supets.pet.module.live.TestLiveCycleActivity;
import com.supets.pet.mvvm.share.TestSharedViewModel;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import lombok.Getter;

import static android.support.constraint.Constraints.TAG;


/**
 * 模块解耦和事件管理者
 */

public class DemoPrenster extends ViewPrenster<DemoView> {

    @ComponentNo()
    @Getter
    private DemoViewModel mDemoViewModel;

    @ComponentNo
    @Getter
    private DemoDataRepository dataRepository;

    public DemoPrenster(DemoView view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getView().getMDelegate().init();
        init();

        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "Disposing subscription from onResume() with untilEvent ON_DESTROY");
                    }
                }).as(AutoDispose.<Long>autoDisposable(AndroidLifecycleScopeProvider.from(getView().getOwner(), Lifecycle.Event.ON_DESTROY)))//OnDestory时自动解绑
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        Log.i(TAG, "Started in onResume(), running until in onDestroy(): " + num);
                    }
                });

    }

    public void init() {

//        SharedViewModel model = ViewModelProviders.of(mView.getOwner()).get(SharedViewModel.class);
//        model.getSelected().observe(mView.getOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                //更新数据
//                mDemoViewModel.updateData(s);
//            }
//        });


//        Transformations.map(mDemoViewModel.getUsers(), new Function<List<String>, String>() {
//            @Override
//            public String apply(List<String> input) {
//                return input.toString();
//            }
//        }).observe(mView.getOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String strings) {
//                mView.mAdapter.updateName(strings);
//                Log.v("一次更新", strings);
//            }
//        });

        mDemoViewModel.users.observe(getView().getOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                getView().getMDelegate().updateName(s);
            }
        });

    }

    public void requestUserName() {
        //获取网络数据
        String data = dataRepository.getData();
        //更新数据
        mDemoViewModel.updateData(data);

        TestSharedViewModel model = ViewModelProviders.of(getView().getFragmentActivity()).get(TestSharedViewModel.class);
        model.select("niha");

    }

    public void startGo() {
        getView().getContext().startActivity(new Intent(getView().getContext(), TestLiveCycleActivity.class));
    }

}
