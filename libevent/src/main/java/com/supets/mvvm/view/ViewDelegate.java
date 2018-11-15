package com.supets.mvvm.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


@Keep
public class ViewDelegate<T extends IView> {

    private T mView;

    public ViewDelegate(T view) {
        this.mView = view;
    }

    public T getView() {
        return mView;
    }

    //内置方法
    public static void clickEvent(View view, @IdRes int id, android.view.View.OnClickListener onClickListener) {
        view.findViewById(id).setOnClickListener(onClickListener);
    }

    public static void gone(View view, @IdRes int id) {
        if (view.findViewById(id).getVisibility() != android.view.View.GONE) {
            view.findViewById(id).setVisibility(android.view.View.GONE);
        }

    }

    public static void invisible(View view, @IdRes int id) {
        if (view.findViewById(id).getVisibility() != android.view.View.INVISIBLE) {
            view.findViewById(id).setVisibility(android.view.View.INVISIBLE);
        }
    }

    public static void visible(View view, @IdRes int id) {
        view.findViewById(id).setVisibility(android.view.View.VISIBLE);
    }

    public static void text(View view, @IdRes int id, CharSequence text) {
        ((TextView) view.findViewById(id)).setText(text);
    }

    public static void background(View view, @IdRes int id, @AnyRes int drawable) {
        view.findViewById(id).setBackgroundResource(drawable);
    }

    public static void colorText(View view, @IdRes int id, @ColorRes int color) {
        ((TextView) view.findViewById(id)).setTextColor(ResourcesCompat.getColor(view.getResources(), color, null));
    }

    public static void drawableLeft(View view, @IdRes int id, @ColorRes int color) {
        ((TextView) view.findViewById(id)).setCompoundDrawables(ResourcesCompat.getDrawable(view.getResources(), color, null), null, null, null);
    }

    public static void enable(View view, @IdRes int id, boolean enable) {
        view.findViewById(id).setEnabled(enable);
    }

    public static void select(View view, @IdRes int id, boolean select) {
        view.findViewById(id).setSelected(select);
    }

    public static void progress(View view, @IdRes int id, int progress, boolean anim) {
        if (Build.VERSION.SDK_INT >= 24) {
            ((ProgressBar) view.findViewById(id)).setProgress(progress, anim);
        } else {
            ((ProgressBar) view.findViewById(id)).setProgress(progress);
        }

    }

    public static int getColor(Context context, @ColorRes int id, @Nullable Resources.Theme theme) {
        return ResourcesCompat.getColor(context.getResources(), id, theme);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int id,
                                       @Nullable Resources.Theme theme) {
        return ResourcesCompat.getDrawable(context.getResources(), id, theme);
    }

    public static void alpha(View view, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        view.setAlpha(alpha);
    }
}
