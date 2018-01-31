package com.supets.pet.mvvm.example;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * 视图模块
 */
public class ViewModelProxy {

    private Context mContext;
    private View mRootView;
    private SparseArray<View> views = new SparseArray<>();

    public ViewModelProxy of(ViewGroup mContext, int xml) {
        this.mContext = mContext.getContext();
        this.mRootView = LayoutInflater.from(mContext.getContext()).inflate(xml, mContext, false);
        this.mRootView.setTag(this);
        return this;
    }

    public ViewModelProxy of(Context mContext, int xml) {
        this.mContext = mContext;
        this.mRootView = LayoutInflater.from(mContext).inflate(xml, null);
        this.mRootView.setTag(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T view(@IdRes int res) {

        View view = views.get(res);
        if (view == null) {
            view = mRootView.findViewById(res);
            views.append(res, view);
        }
        return (T) view;
    }

    public ViewModelProxy clickEvent(@IdRes int res, View.OnClickListener onClickListener) {
        view(res).setOnClickListener(onClickListener);
        return this;
    }

    public ViewModelProxy gone(@IdRes int id) {
        view(id).setVisibility(View.GONE);
        return this;
    }

    public ViewModelProxy invisible(@IdRes int id) {
        view(id).setVisibility(View.INVISIBLE);
        return this;
    }

    public ViewModelProxy visible(@IdRes int id) {
        view(id).setVisibility(View.VISIBLE);
        return this;
    }

    public ViewModelProxy text(@IdRes int id, CharSequence text) {
        ((TextView) view(id)).setText(text);
        return this;
    }

    public ViewModelProxy background(@IdRes int id, @AnyRes int drawable) {
        view(id).setBackgroundResource(drawable);
        return this;
    }

    public ViewModelProxy color(@IdRes int id, @ColorRes int color) {
        ((TextView) view(id)).setTextColor(ResourcesCompat.getColor(getContext().getResources(), color, null));
        return this;
    }

    public ViewModelProxy drawableLeft(@IdRes int id, @ColorRes int color) {
        ((TextView) view(id)).setCompoundDrawables(ResourcesCompat.getDrawable(getContext().getResources(), color, null), null, null, null);
        return this;
    }

    public ViewModelProxy enable(@IdRes int id, boolean enable) {
        view(id).setEnabled(enable);
        return this;
    }

    public ViewModelProxy select(@IdRes int id, boolean select) {
        view(id).setSelected(select);
        return this;
    }

    public ViewModelProxy progress(@IdRes int id, int progress, boolean anim) {
        if (Build.VERSION.SDK_INT >= 24) {
            ((ProgressBar) view(id)).setProgress(progress, anim);
        } else {
            ((ProgressBar) view(id)).setProgress(progress);
        }
        return this;
    }

    public Context getContext() {
        return mContext;
    }

    public FragmentActivity getOwner() {
        return (FragmentActivity) mContext;
    }

    public View rootView() {
        return mRootView;
    }

}