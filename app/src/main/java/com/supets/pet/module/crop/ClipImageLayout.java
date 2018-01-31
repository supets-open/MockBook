package com.supets.pet.module.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ClipImageLayout extends RelativeLayout {

    private ClipZoomImageView mZoomImageView;
    private ClipImageBorderView mClipImageView;

    public ClipImageBorderView getClipImageView() {
        return mClipImageView;
    }

    public ClipImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mZoomImageView = new ClipZoomImageView(context, attrs);
        mClipImageView = new ClipImageBorderView(context, attrs);
        this.addView(mZoomImageView, -1, -1);
        this.addView(mClipImageView, -1, -1);
    }

    public void setAspectRatio(int mAspectRatioX, int mAspectRatioY) {
        mZoomImageView.setAspectRatio(mAspectRatioX, mAspectRatioY);
        mClipImageView.setAspectRatio(mAspectRatioX, mAspectRatioY);
    }

    public void setHorizontalPadding(int mHorizontalPadding, int verticalPadding) {
        mZoomImageView.setHorizontalPadding(mHorizontalPadding, verticalPadding);
        mClipImageView.setHorizontalPadding(mHorizontalPadding, verticalPadding);
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
