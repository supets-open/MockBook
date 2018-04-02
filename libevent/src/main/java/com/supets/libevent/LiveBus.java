package com.supets.libevent;

import android.arch.lifecycle.MutableLiveData;

/**
 * <p>
 * <li>在**非主线程**中使用 postValue()</li>
 * LiveBus.getInstance().postValue(event);
 * <p>在**主线程**中使用 setValue()
 * LiveBus.getInstance().setValue(event);
 * <p>
 * 在 LifecycleActivity/LifecycleFragment 中订阅事件，
 * 订阅事件的回调都将在**主线程**中执行；
 * // observe() 仅仅在 onStart() 与 onPause() 之间时执行回调；
 * // observeForever() 在任何生命周期都可以回调，所以**务必**手动移除回调
 * LiveBus.getInstance().observe(this, new Observer<Object>() {
 *
 * @Override public void onChanged(@Nullable Object event) {
 * // Event 通常都是继承自 Object 的，
 * // 要想订阅某个指定的 Event 类型，你还需要自己 cast 一下
 * // ...
 * }
 * });
 * </p>
 */

//???  每次更换不同的owner，默认会分发最后一次值。
//LiveData  static会记忆最后一次，默认会转发
public final class LiveBus extends MutableLiveData<Object> {

    private static final class LiveBusHolder {
        private static final LiveBus INSTANCE = new LiveBus();
    }

    private LiveBus() {
    }

    public static LiveBus getInstance() {
        return LiveBusHolder.INSTANCE;
    }

}