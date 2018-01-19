package com.supets.pet.mock.ui.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

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

        // 计算padding的px
//		mHorizontalPadding = (int) TypedValue.applyDimension(
//				TypedValue.COMPLEX_UNIT_DIP, mHorizontalPadding, getResources()
//						.getDisplayMetrics());
        mZoomImageView.setHorizontalPadding(mHorizontalPadding);
        mClipImageView.setHorizontalPadding(mHorizontalPadding);
    }

    /**
     * 对外公布设置边距的方法,单位为dp
     *
     * @param mHorizontalPadding
     */
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
