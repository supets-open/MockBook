package com.supets.pet.mvvm.mylivedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ActionTransformations {

    @MainThread
    public static <X, Y> ActionLiveData<Y> map(@NonNull ActionLiveData<X> source,
                                               @NonNull final Function<X, Y> func) {
        final ActionLiveData<Y> result = new ActionLiveData<>();
        result.addSource(source, new ActionObserver<X>() {
            @Override
            public void onChanged(@Nullable X x) {
                result.setValue(func.apply(x));
            }

            @Override
            public void onAction(int id, Object... args) {
                result.setAction(id, args);
            }
        });
        return result;
    }

    @MainThread
    public static <X, Y> ActionLiveData<Y> switchMap(@NonNull LiveData<X> trigger,
                                                     @NonNull final Function<X, ActionLiveData<Y>> func) {
        final ActionLiveData<Y> result = new ActionLiveData<>();
        result.addSource(trigger, new Observer<X>() {
            ActionLiveData<Y> mSource;

            @Override
            public void onChanged(@Nullable X x) {
                ActionLiveData<Y> newLiveData = func.apply(x);
                if (mSource == newLiveData) {
                    return;
                }
                if (mSource != null) {
                    result.removeSource(mSource);
                }
                mSource = newLiveData;
                if (mSource != null) {
                    result.addSource(mSource, new ActionObserver<Y>() {
                        @Override
                        public void onAction(int id, Object... args) {
                            result.setAction(id, args);
                        }

                        @Override
                        public void onChanged(@Nullable Y y) {
                            result.setValue(y);
                        }
                    });
                }
            }
        });
        return result;
    }
}