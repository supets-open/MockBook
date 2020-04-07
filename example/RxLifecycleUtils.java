package com.supets.pet.mvvm.example;


import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import androidx.lifecycle.LifecycleOwner;

public class RxLifecycleUtils {

    private RxLifecycleUtils() {
        throw new IllegalStateException("Can't instance the RxLifecycleUtils");
    }

    public static AutoDisposeConverter bindLifecycle(LifecycleOwner lifecycleOwner) {
        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(lifecycleOwner)
        );
    }
}