package com.supets.pet.mock.ui.crop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.supets.pet.mockui.R;

public class ClipImageLayout extends RelativeLayout {

    private ClipZoomImageView mZoomImageView;
    private ClipImageBorderView mClipImageView;

    private int mHorizontalPadding = 0;

    public ClipImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mZoomImageView = new ClipZoomImageView(context);
        mClipImageView = new ClipImageBorderView(context);
        android.view.ViewGroup.LayoutParams lp = new LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(mZoomImageView, lp);
        this.addView(mClipImageView, lp);


        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClipImageLayout, 0, 0);
        int mAspectRatioX = typedArray.getInteger(R.styleable.ClipImageLayout_ratioX, 1);
        int mAspectRatioY = typedArray.getInteger(R.styleable.ClipImageLayout_ratioY, 1);
        int horizontalPadding = typedArray.getInteger(R.styleable.ClipImageLayout_horizontalPadding, 0);
        typedArray.recycle();
        int mHorizontalPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, horizontalPadding, getResources()
                        .getDisplayMetrics());
        mZoomImageView.setAspectRatio(mAspectRatioX, mAspectRatioY);
        mZoomImageView.setHorizontalPadding(mHorizontalPadding);
        mClipImageView.setAspectRatio(mAspectRatioX, mAspectRatioY);
        mClipImageView.setHorizontalPadding(mHorizontalPadding);
    }

    public void setHorizontalPadding(int mHorizontalPadding) {
        this.mHorizontalPadding = mHorizontalPadding;
        mZoomImageView.setHorizontalPadding(mHorizontalPadding);
        mClipImageView.setHorizontalPadding(mHorizontalPadding);
    }

    public void setZoomImageView(int res) {
        mZoomImageView.setImageDrawable(getResources().getDrawable(res));
    }

    public void setZoomImageView(Bitmap bm) {
        mZoomImageView.setImageBitmap(bm);
    }

    public ClipZoomImageView getZoomImageView() {
        return mZoomImageView;
    }

    public void setImageBitmap(Bitmap mBitmap) {
        setZoomImageView(mBitmap);
    }

}
