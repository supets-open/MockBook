package com.supets.pet.mvvm.mylivedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ActionLiveData<T> extends MediatorLiveData<T> implements ActionCreator {

    private Set<ActionObserver<T>> actionObservers = new HashSet<>();
    private boolean active;

    private ActionEntity actionEntity;

    private Handler handler;
    private Runnable actionRun = new Runnable() {
        @Override
        public void run() {
            dispatchAction();
        }
    };

    /**
     * 通知Observer更新事件
     */
    private void dispatchAction() {
        if (active) {
            for (ActionObserver<T> actionObserver : actionObservers) {
                actionObserver.onAction(actionEntity.id, actionEntity.extra);
            }
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        active = true;
        for (Map.Entry<ActionLiveData<?>, ActionSource<?>> entry : mHandlers.entrySet()) {
            entry.getValue().plug();
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        active = false;
        for (Map.Entry<ActionLiveData<?>, ActionSource<?>> entry : mHandlers.entrySet()) {
            entry.getValue().unplug();
        }
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        super.observe(owner, observer);
        if (observer instanceof ActionObserver) {
            actionObservers.add((ActionObserver<T>) observer);
        }
    }

    @Override
    public void removeObserver(@NonNull Observer<T> observer) {
        super.removeObserver(observer);
        if (observer instanceof ActionObserver) {
            actionObservers.remove(observer);
        }
    }

    /**
     * 设置事件
     * @param id 事件id
     * @param args 可选的参数
     */
    @Override
    public void setAction(int id, Object... args) {
        actionEntity = new ActionEntity(id, args);
        if (isMainThread()) {
            dispatchAction();
        } else {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(actionRun);
        }
    }

    public boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    /****支持Transformations的转换***/

    private Map<ActionLiveData<?>, ActionSource<?>> mHandlers = new HashMap<>();

    @Override
    public <S> void addSource(@NonNull LiveData<S> source, @NonNull Observer<S> onChanged) {
        super.addSource(source, onChanged);
        if (source instanceof ActionLiveData && onChanged instanceof ActionObserver) {
            addActionObserver((ActionLiveData<S>) source, (ActionObserver<S>) onChanged);
        }
    }

    protected <S> void addActionObserver(ActionLiveData<S> source, ActionObserver<S> actionObserver) {
        ActionSource<S> actionSource = new ActionSource<>(source, actionObserver);
        ActionSource<?> existing = mHandlers.put(source, actionSource);
        if (existing != null) {
            return;
        }
        if (hasActiveObservers()) {
            actionSource.plug();
        }
    }

    @Override
    public <S> void removeSource(@NonNull LiveData<S> toRemote) {
        super.removeSource(toRemote);
        if (toRemote instanceof ActionLiveData) {
            removeActionSource(toRemote);
        }
    }

    protected <S> void removeActionSource(@NonNull LiveData<S> toRemote) {
        ActionSource<?> source = mHandlers.remove(toRemote);
        if (source != null) {
            source.unplug();
        }
    }

    public static class ActionSource<T> implements ActionHandler {

        ActionLiveData<T> actionLiveData;
        ActionObserver<T> actionObserver;

        public ActionSource(ActionLiveData<T> actionLiveData, ActionObserver<T> actionObserver) {
            this.actionLiveData = actionLiveData;
            this.actionObserver = actionObserver;
        }

        void plug() {
            actionLiveData.observeForever(actionObserver);
        }

        void unplug() {
            actionLiveData.removeObserver(actionObserver);
        }

        @Override
        public void onAction(int id, Object... args) {
            actionObserver.onAction(id, args);
        }
    }
}