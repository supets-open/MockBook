package com.supets.pet.mock.ui.crop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.supets.commons.utils.UIUtils;
import com.supets.pet.mockui.R;

public class ClipImageBorderView extends View {
    /**
     * 水平方向与View的边距
     */
    private int mHorizontalPadding;
    /**
     * 垂直方向与View的边距
     */
    private int mVerticalPadding;
    /**
     * 边框的颜色，默认为白色
     */
    private int mBorderColor = Color.parseColor("#FFFFFF");
    /**
     * 边框的宽度 单位dp
     */
    private int mBorderWidth = 4;
    private Paint mPaint;
    public int mCornerLength = 70;

    private int mAspectRatioX;
    private int mAspectRatioY;
    private int mTopBottomPadding;
    private int mLeftRightPadding;

    public ClipImageBorderView(Context context) {
        this(context, null);
    }

    public ClipImageBorderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ClipImageBorderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClipImageLayout, 0, 0);
        mAspectRatioX = typedArray.getInteger(R.styleable.ClipImageLayout_ratioX, 1);
        mAspectRatioY = typedArray.getInteger(R.styleable.ClipImageLayout_ratioY, 1);
        int horizontalPadding = typedArray.getInteger(R.styleable.ClipImageLayout_horizontalPadding, 0);
        int verticalPadding = typedArray.getInteger(R.styleable.ClipImageLayout_verticalPadding, 0);
        typedArray.recycle();
        mLeftRightPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, horizontalPadding, getResources()
                        .getDisplayMetrics());
        mTopBottomPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, verticalPadding, getResources()
                        .getDisplayMetrics());
        init();
    }

    private void init() {
        mBorderWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mBorderWidth, getResources()
                        .getDisplayMetrics());
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mCornerLength = UIUtils.getScreenWidth() / 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 计算矩形区域的宽度
        // 计算距离屏幕垂直边界 的边距
        float ratioXY = mAspectRatioX * 1F / mAspectRatioY;
        if (ratioXY < 1.0) {
            mHorizontalPadding = (int) ((getWidth() - (getHeight() - 2 * mTopBottomPadding) * ratioXY) / 2);
            mVerticalPadding = mTopBottomPadding;
        } else {
            mVerticalPadding = (int) ((getHeight() - (getWidth() - 2 * mLeftRightPadding) / ratioXY) / 2);
            mHorizontalPadding = mLeftRightPadding;
        }

        // 绘制外边框阴影
        mPaint.setColor(Color.parseColor("#aa000000"));
        mPaint.setStyle(Style.FILL);
        drawBackground(canvas);
        //绘制内边框
        mPaint.setColor(Color.parseColor("#eeeeee"));
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Style.STROKE);
        canvas.drawRect(mHorizontalPadding, mVerticalPadding, getWidth()
                - mHorizontalPadding, getHeight() - mVerticalPadding, mPaint);
        //绘制圆角
        mPaint.setColor(mBorderColor);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setStyle(Style.STROKE);
        drawCorner(canvas);

    }


    public void drawBackground(Canvas canvas) {
        // 绘制左边1
        canvas.drawRect(0, 0, mHorizontalPadding, getHeight(), mPaint);
        // 绘制右边2
        canvas.drawRect(getWidth() - mHorizontalPadding, 0, getWidth(),
                getHeight(), mPaint);
        // 绘制上边3
        canvas.drawRect(mHorizontalPadding, 0, getWidth() - mHorizontalPadding,
                mVerticalPadding, mPaint);
        // 绘制下边4
        canvas.drawRect(mHorizontalPadding, getHeight() - mVerticalPadding,
                getWidth() - mHorizontalPadding, getHeight(), mPaint);
    }


    private void drawCorner(Canvas canvas) {
        canvas.drawLine(mHorizontalPadding,
                mVerticalPadding + mBorderWidth / 2,
                mHorizontalPadding + mCornerLength,
                mVerticalPadding + mBorderWidth / 2, mPaint);
        canvas.drawLine(mHorizontalPadding + mBorderWidth / 2,
                mVerticalPadding,
                mHorizontalPadding + mBorderWidth / 2,
                mVerticalPadding + mCornerLength, mPaint);

        canvas.drawLine(getWidth() - mHorizontalPadding - mCornerLength,
                mVerticalPadding + mBorderWidth / 2,
                getWidth() - mHorizontalPadding,
                mVerticalPadding + mBorderWidth / 2, mPaint);
        canvas.drawLine(getWidth() - mHorizontalPadding - mBorderWidth / 2,
                mVerticalPadding,
                getWidth() - mHorizontalPadding - mBorderWidth / 2,
                mVerticalPadding + mCornerLength, mPaint);

        //左下角和右下角
        canvas.drawLine(mHorizontalPadding,
                getHeight() - mVerticalPadding - mBorderWidth / 2,
                mHorizontalPadding + mCornerLength,
                getHeight() - mVerticalPadding - mBorderWidth / 2, mPaint);
        canvas.drawLine(mHorizontalPadding + mBorderWidth / 2,
                getHeight() - mVerticalPadding,
                mHorizontalPadding + mBorderWidth / 2,
                getHeight() - mVerticalPadding - mCornerLength, mPaint);

        canvas.drawLine(getWidth() - mHorizontalPadding - mCornerLength,
                getHeight() - mVerticalPadding - mBorderWidth / 2,
                getWidth() - mHorizontalPadding,
                getHeight() - mVerticalPadding - mBorderWidth / 2, mPaint);
        canvas.drawLine(getWidth() - mHorizontalPadding - mBorderWidth / 2,
                getHeight() - mVerticalPadding,
                getWidth() - mHorizontalPadding - mBorderWidth / 2,
                getHeight() - mVerticalPadding - mCornerLength, mPaint);
    }

    public void setHorizontalPadding(int mHorizontalPadding, int mVerticalPadding) {
        this.mLeftRightPadding = mHorizontalPadding;
        this.mTopBottomPadding = mVerticalPadding;
    }

    public void setAspectRatio(int mAspectRatioX, int mAspectRatioY) {
        this.mAspectRatioX = mAspectRatioX;
        this.mAspectRatioY = mAspectRatioY;
        fresh();
    }

    public void fresh() {
        invalidate();
    }
}
