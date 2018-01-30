package com.supets.pet.mock.ui.paste;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;

import com.supets.commons.utils.UIUtils;
import com.supets.pet.mockui.R;

import java.io.FileOutputStream;


public class XGPasterView extends View implements OnScaleGestureListener {

	private static final int MaxDrawDash = 200;

	private static final int OPER_DEFAULT2 = 2; // 默认
	private static final int OPER_DEFAULT = -1; // 默认
	private static final int OPER_TRANSLATE = 0; // 移动
	private static final int OPER_ROTATE_SCALE = 4; // 旋转并缩放
	//private static final int DEFAULT_WIDTH = 120; // 默认宽度dp
	private static final float IMAGE_PADDING = 0.5f; // 图片离边框的宽度dp

	private Context context;

	private Bitmap mainBmp, controlBmp,controlDelBmp;
	private int mainBmpWidth, mainBmpHeight;
	private int controlBmpWidth, controlBmpHeight;
	private int controlDelBmpWidth, controlDelBmpHeight;

	private Matrix matrix, dashMatrix;
	private float[] srcPs, dstPs, dashPs;

	private RectF srcRect, dstRect;
	private Paint paint, paintRect, paintFrame, paintFrameDash;

	private float deltaX = 0, deltaY = 0; // 位移值
	private float scaleValue = 1; // 缩放值
	private PointF lastPoint;
	private PointF prePivot, lastPivot;
	private float degrees;

	private int mWidth;
	private int mHeight;

	private int mImagePadding;
	private int mDefaultWidth;

	private int cx = 2, cy = 3;

	private int lastOper = OPER_DEFAULT;

	private boolean isUsed, drawDash;

	private long pressDownTime;
	private PointF mPressDown;

	private OnRemoveListener mListener;
	private int xoff = 0, yoff = 0;

	private OnTipListener mOnTipListener;

	private ScaleGestureDetector dector;

	public XGPasterView(Context context) {
		this(context, null);
	}

	public XGPasterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initData();
	}

	private void initData() {
		dector = new ScaleGestureDetector(context, this);
		controlBmp = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.icon_paster_control);
		controlBmpWidth = controlBmp.getWidth();
		controlBmpHeight = controlBmp.getHeight();


		controlDelBmp = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.paste_delete);
		controlDelBmpWidth = controlDelBmp.getWidth();
		controlDelBmpHeight = controlDelBmp.getHeight();


		mImagePadding = UIUtils.dp2px(IMAGE_PADDING, getContext());
		//mDefaultWidth = DeviceScreenUtils.dp2px(DEFAULT_WIDTH, getContext());
		mDefaultWidth = (int) (UIUtils.getScreenWidth(getContext())*0.4);

		lastPoint = new PointF(0, 0);

		matrix = new Matrix();
		dashMatrix = new Matrix();
		paint = new Paint();
		paint.setAntiAlias(true);

		paintRect = new Paint();
		paintRect.setColor(Color.RED);
		paintRect.setAlpha(100);
		paintRect.setAntiAlias(true);

		paintFrame = new Paint();
		paintFrame.setStrokeWidth(2);
		paintFrame.setColor(0xB2FFFFFF);
		paintFrame.setAntiAlias(true);

		paintFrameDash = new Paint(paintFrame);
		paintFrameDash.setStyle(Paint.Style.STROKE);
		PathEffect pathEffect = new DashPathEffect(new float[] { 20, 10 }, 1);
		paintFrameDash.setPathEffect(pathEffect);

		setVisibility(View.GONE);
		setClickable(true);//单击事件打开，不打开触摸移动没反应
	}

	public void setOnRemoveListener(OnRemoveListener listener) {
		mListener = listener;
	}

	public void setOnTipListener(OnTipListener listener) {
		mOnTipListener = listener;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void removePaster() {
		isUsed = false;
		setVisibility(View.GONE);
	}

	private int max = 1;
	private int current = 1;

	public void setPaster(Bitmap paster, int width, int height, int max, int current, float scale, float xoff, float yoff) {

		this.xoff= (int) (xoff);
		this.yoff= (int) (yoff);

		mDefaultWidth = (int) (UIUtils.getScreenWidth(getContext())*scale);

		this.max = max;
		this.current = current;
		matrix.reset();
		dashMatrix.reset();
		isUsed = true;
		setVisibility(View.VISIBLE);

		mainBmp = paster;
		mainBmpWidth = mainBmp.getWidth();
		mainBmpHeight = mainBmp.getHeight();

		srcRect = new RectF(-mImagePadding, -mImagePadding, mainBmpWidth + mImagePadding, mainBmpHeight + mImagePadding);
		dstRect = new RectF();
		srcPs = new float[] { srcRect.left, srcRect.top, srcRect.right, srcRect.top, srcRect.right, srcRect.bottom,
				srcRect.left, srcRect.bottom, mainBmpWidth / 2, mainBmpHeight / 2 };
		dstPs = srcPs.clone();
		dashPs = srcPs.clone();

		prePivot = new PointF(mainBmpWidth / 2, mainBmpHeight / 2);
		lastPivot = new PointF(mainBmpWidth / 2, mainBmpHeight / 2);

		setSize(width, height);
		setMatrix(OPER_DEFAULT2); // 移动到画布中央
	}

	public void setPaster(Bitmap paster, int width, int height, int max, int current, float scale) {

		mDefaultWidth = (int) (UIUtils.getScreenWidth(getContext())*scale);

		this.max = max;
		this.current = current;
		matrix.reset();
		dashMatrix.reset();
		isUsed = true;
		setVisibility(View.VISIBLE);

		mainBmp = paster;
		mainBmpWidth = mainBmp.getWidth();
		mainBmpHeight = mainBmp.getHeight();

		srcRect = new RectF(-mImagePadding, -mImagePadding, mainBmpWidth + mImagePadding, mainBmpHeight + mImagePadding);
		dstRect = new RectF();
		srcPs = new float[] { srcRect.left, srcRect.top, srcRect.right, srcRect.top, srcRect.right, srcRect.bottom,
				srcRect.left, srcRect.bottom, mainBmpWidth / 2, mainBmpHeight / 2 };
		dstPs = srcPs.clone();
		dashPs = srcPs.clone();

		prePivot = new PointF(mainBmpWidth / 2, mainBmpHeight / 2);
		lastPivot = new PointF(mainBmpWidth / 2, mainBmpHeight / 2);

		setSize(width, height);
		setMatrix(OPER_DEFAULT); // 移动到画布中央
	}


	// 缩放文件平移
	public void compound(String path) throws Exception {
		Bitmap src = BitmapFactory.decodeFile(path);

		Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);

		float scaleFactor = 1.0f * src.getWidth() / mWidth;
		matrix.postScale(scaleFactor, scaleFactor, mWidth / 2, mHeight / 2);

		float dx = (src.getWidth() - mWidth) * 1.0f / 2;
		float dy = (src.getHeight() - mHeight) * 1.0f / 2;
		matrix.postTranslate(dx, dy);

		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(src, 0, 0, paint);
		canvas.drawBitmap(mainBmp, matrix, paint);
		FileOutputStream stream;
		stream = new FileOutputStream(path);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		src.recycle();
		bitmap.recycle();
	}

	public Bitmap getPasterBitmap() {
		return mainBmp;
	}

	public Matrix getPasterMatrix(Bitmap src) {

		Matrix tempmatrix=new Matrix(matrix);


		float scaleFactor = 1.0f * src.getWidth() / mWidth;
		tempmatrix.postScale(scaleFactor, scaleFactor, mWidth / 2, mHeight / 2);

		float dx = (src.getWidth() - mWidth) * 1.0f / 2;
		float dy = (src.getHeight() - mHeight) * 1.0f / 2;
		tempmatrix.postTranslate(dx, dy);
		return tempmatrix;
	}

	public void setSize(int width, int height) {
		// mWidth = ((View) getParent()).getWidth();
		// mHeight = ((View) getParent()).getHeight();
		mWidth = width;
		mHeight = height;

		requestLayout();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (mWidth > 0 || mHeight > 0) {
			setMeasuredDimension(mWidth, mHeight);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	private void setMatrix(int operationType) {
		switch (operationType) {
			case OPER_TRANSLATE:
				matrix.postTranslate(deltaX, deltaY);
				dashMatrix.postTranslate(deltaX, deltaY);
				break;
			case OPER_ROTATE_SCALE:
				if (!drawDash) {
					dashMatrix.set(matrix);
					dashMatrix.postScale(scaleValue, scaleValue, dstPs[8], dstPs[9]);
				}
				dashMatrix.postRotate(degrees, dstPs[8], dstPs[9]);

				matrix.postScale(scaleValue, scaleValue, dstPs[8], dstPs[9]);
				matrix.postRotate(degrees, dstPs[8], dstPs[9]);
				break;
			case OPER_DEFAULT:
				xoff = (mainBmpWidth / 2 / max) * current;
				yoff = (mainBmpHeight / 2 / max) * current;

				float dx = mWidth / 2 - mainBmpWidth / 2 + xoff;
				float dy = mHeight / 2 - mainBmpHeight / 2 + yoff;
				matrix.postTranslate(dx, dy);
				dashMatrix.postTranslate(dx, dy);
				scaleToDefaultWidth();
				break;
			case OPER_DEFAULT2:

				float scaleFactory = 1.0f * mDefaultWidth / Math.max(mainBmpWidth, mainBmpHeight);
				float px = 0;
				float py = 0;
				matrix.postScale(scaleFactory, scaleFactory, px, py);
				dashMatrix.postScale(scaleFactory, scaleFactory, px, py);

				translate(xoff,yoff);

				break;
		}

		double width = MathUtils.getDistanceOfTwoPoints(dstPs[0], dstPs[1], dstPs[2], dstPs[3]);
		double height = MathUtils.getDistanceOfTwoPoints(dstPs[2], dstPs[3], dstPs[4], dstPs[5]);
		drawDash = Math.max(width, height) < MaxDrawDash;

		dashMatrix.mapPoints(dashPs, srcPs);

		matrix.mapPoints(dstPs, srcPs);
		matrix.mapRect(dstRect, srcRect);
	}

	private void scaleToDefaultWidth() {
		float scaleFactory = 1.0f * mDefaultWidth / Math.max(mainBmpWidth, mainBmpHeight);
		float px = mWidth / 2;
		float py = mHeight / 2;
		matrix.postScale(scaleFactory, scaleFactory, px, py);
		dashMatrix.postScale(scaleFactory, scaleFactory, px, py);
	}

	private boolean isOnControlPoint2(int evx, int evy) {
		Rect rect = new Rect(evx - controlBmpWidth / 2, evy - controlBmpHeight / 2, evx + controlBmpWidth / 2, evy
				+ controlBmpHeight / 2);
		return rect.contains((int) dashPs[cx], (int) dashPs[cy]);
	}

	private boolean isOnControlPoint3(int evx, int evy) {
		Rect rect = new Rect(evx-controlDelBmpWidth / 2, evy - controlDelBmpHeight / 2, evx + controlDelBmpWidth / 2, evy
				+ controlDelBmpHeight / 2);
		return rect.contains((int) dashPs[6], (int) dashPs[7]);
	}

	private int getOperationType2(MotionEvent event) {
		int evX = (int) event.getX();
		int evY = (int) event.getY();

		switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				lastOper = OPER_DEFAULT;
				return OPER_DEFAULT;

			case MotionEvent.ACTION_DOWN:
				lastOper = isOnControlPoint2(evX, evY) ? OPER_ROTATE_SCALE : OPER_TRANSLATE;
				return OPER_DEFAULT;

			case MotionEvent.ACTION_MOVE:
				if (lastOper != OPER_DEFAULT) {
					return lastOper;
				}
				lastOper = isOnControlPoint2(evX, evY) ? OPER_ROTATE_SCALE : OPER_TRANSLATE;
				return lastOper;

			default:
				return OPER_DEFAULT;
		}
	}

	private boolean firstTap = false;
	private boolean sencondtap = false;
	private boolean firstSelectTap = false;
	private boolean sencondSelecttap = false;
	private boolean tipFlag = false;
	private boolean scaleflag = false;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		int evX = (int) event.getX(event.getActionIndex());
		int evY = (int) event.getY(event.getActionIndex());

		if (MotionEventCompat.getPointerCount(event) > 2) {
			// .v("XGPasterView", "超过2个手指了");
			return false;
		}
		if (!firstSelectTap && !sencondSelecttap) {
			// Log.v("XGPasterView", "所有手指离开了");
			firstTap = false;
			sencondtap = false;
			tipFlag = false;
		}

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				PointF[] polygon = new PointF[4];
				for (int i = 0; i < 4; i++) {
					polygon[i] = new PointF((int) dashPs[i * 2], (int) dashPs[i * 2 + 1]);
				}
				Rect rect = new Rect(evX - controlBmpWidth / 2, evY - controlBmpHeight / 2, evX + controlBmpWidth / 2, evY
						+ controlBmpHeight / 2);


				Rect rect1 = new Rect(evX - controlDelBmpWidth / 2, evY - controlDelBmpHeight / 2, evX + controlDelBmpWidth / 2, evY
						+ controlDelBmpHeight / 2);

				// Log.v("XGPasterView", "第一手指按下了");
				firstSelectTap = true;
				if (!rect1.contains((int) dashPs[6], (int) dashPs[7])
						&&!rect.contains((int) dashPs[cx], (int) dashPs[cy])
						&& !MathUtils.pinplg(polygon.length, polygon, new PointF(evX, evY))) {
					firstTap = false;
					if (mOnTipListener != null) {
						mOnTipListener.hideAll(!firstTap);
					}
					// Log.v("XGPasterView", "第一手指在图片区域外");
					// return true;
				} else {
					// Log.v("XGPasterView", "第一手指在图片区域内");
					firstTap = true;
				}
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				// Log.v("XGPasterView", evX + "==" + evY);
				PointF[] polygon2 = new PointF[4];
				for (int i = 0; i < 4; i++) {
					polygon2[i] = new PointF((int) dashPs[i * 2], (int) dashPs[i * 2 + 1]);
				}
				Rect rect2 = new Rect(evX - controlBmpWidth / 2, evY - controlBmpHeight / 2, evX + controlBmpWidth / 2, evY
						+ controlBmpHeight / 2);

				Rect rect3 = new Rect(evX - controlDelBmpWidth / 2, evY - controlDelBmpHeight / 2, evX + controlDelBmpWidth / 2, evY
						+ controlDelBmpHeight / 2);

				sencondSelecttap = true;
				// Log.v("XGPasterView", "第二手指按下了");
				if (!rect2.contains((int) dashPs[cx], (int) dashPs[cy])
						&&!rect3.contains((int) dashPs[6], (int) dashPs[7])
						&& !MathUtils.pinplg(polygon2.length, polygon2, new PointF(evX, evY))) {
					sencondtap = false;
					// Log.v("XGPasterView", "第二手指在图片区域外");
					// return false;
				} else {
					// Log.v("XGPasterView", "第二手指在图片区域内");
					sencondtap = true;
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				// Log.v("XGPasterView", "第二手指离开了");
				sencondSelecttap = false;
				break;
			case MotionEvent.ACTION_UP:
				// Log.v("XGPasterView", "第一手指离开了");
				firstSelectTap = false;
				break;
		}

		if (tipFlag && (sencondtap && firstTap)) {
			// Log.v("XGPasterView", "屏幕有2个手指，2个手指在区域内");
			dector.onTouchEvent(event);
			return true;
		}
		if (tipFlag) {
			// Log.v("XGPasterView", "屏幕有2个手指，在测试1个离开先后顺序");
			sencondtap = false;
			firstTap = false;
			return true;
		}
		if (MotionEventCompat.getPointerCount(event) <= 1 && !firstTap) {
			// Log.v("XGPasterView", "屏幕有1个手指，在图片区域外面");
			return false;
		}
		if (MotionEventCompat.getPointerCount(event) > 1) {
			// Log.v("XGPasterView", "屏幕有2个手指，进入缩放模式");
			if (!tipFlag) {
				tipFlag = true;
			}
			return true;
		}

		// Log.v("XGPasterView", "屏幕有1个手指，在图片区域内");

		if (getParent() != null) {
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
				case MotionEvent.ACTION_MOVE:
					getParent().requestDisallowInterceptTouchEvent(true);
					break;

				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_UP:

					getParent().requestDisallowInterceptTouchEvent(false);
					break;
			}
		}

		int operType = getOperationType2(event);

		switch (operType) {
			case OPER_TRANSLATE:
				translate(evX, evY);
				break;

			case OPER_ROTATE_SCALE:
				rotateAndScale(event);
				break;
		}

		lastPoint.x = evX;
		lastPoint.y = evY;

		invalidate();

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mPressDown = new PointF(event.getX(), event.getY());
				pressDownTime = System.currentTimeMillis();
				break;

			case MotionEvent.ACTION_UP:
				needRemovePaster();
				PointF point = new PointF(event.getX(), event.getY());
				double distance = MathUtils.getDistanceOfTwoPoints(mPressDown, point);
				long interval = System.currentTimeMillis() - pressDownTime;
				if (interval > 200 || distance > 8) { // down 和 up 时间差大于200, 或者距离大于5
					// 不视为 click事件
					return true;
				}else{
					if (mOnPasteViewListener!=null&&isOnControlPoint2(evX,evY)){
						mOnPasteViewListener.onClickControlArea();
						return true;
					}else if (mOnPasteViewListener!=null&&isOnControlPoint3(evX,evY)&&isShow){
						mOnPasteViewListener.onClickDelArea();
						return true;
					}else if (mOnPasteViewListener!=null){
						mOnPasteViewListener.onClickPasteArea();
						return true;
					}
				}
				break;
		}

		return super.dispatchTouchEvent(event);
	}

	private void needRemovePaster() {
		RectF rect = new RectF(0, 0, mWidth, mHeight);
		RectF small = new RectF(dstRect);
		small.top -= controlBmpHeight / 2;
		small.right += controlBmpWidth / 2;
		if (mListener != null && !rect.intersect(small)) {
			mListener.onRemove();
		}
	}

	private void translate(int evx, int evy) {
		prePivot.x += evx - lastPoint.x;
		prePivot.y += evy - lastPoint.y;

		deltaX = prePivot.x - lastPivot.x;
		deltaY = prePivot.y - lastPivot.y;

		lastPivot.x = prePivot.x;
		lastPivot.y = prePivot.y;

		setMatrix(OPER_TRANSLATE); // 设置矩阵

	}

	private boolean isOnlyScaled=false;

	private void rotateAndScale(MotionEvent event) {
		if (isShow) {
			scale(event);
			if (!isOnlyScaled){
				rotate(event);
			}
			setMatrix(OPER_ROTATE_SCALE);
		}
	}

	public void setOnlyScaled(boolean isOnlyScaled){
	  this.isOnlyScaled=isOnlyScaled;
	}

	private void scale(MotionEvent event) {
		float preX = dstPs[cx];
		float preY = dstPs[cy];
		float x = event.getX();
		float y = event.getY();
		float centerX = dstPs[8];
		float centerY = dstPs[9];

		double preDistance = MathUtils.getDistanceOfTwoPoints(preX, preY, centerX, centerY);
		double nowDistance = MathUtils.getDistanceOfTwoPoints(x, y, centerX, centerY);

		this.scaleValue = (float) (nowDistance / preDistance);
		this.scaleValue = Float.isNaN(this.scaleValue) ? 1 : this.scaleValue;

	}

	private void rotate(MotionEvent event) {
		PointF center = new PointF(dstPs[8], dstPs[9]);
		PointF start = new PointF(dstPs[cx], dstPs[cy]);
		PointF end = new PointF(event.getX(), event.getY());
		degrees = (float) MathUtils.calcDegrees(center, start, end);
	}

	@Override
	public void onDraw(Canvas canvas) {
		drawBackground(canvas);// 绘制背景
		canvas.drawBitmap(mainBmp, matrix, paint);// 绘制图片
		if (isShow) {
			drawFrame(canvas);// 绘制边框
			drawControlPoints(canvas); // 绘制控制点图片
		}
	}

	private void drawBackground(Canvas canvas) {
		// canvas.drawRect(dstRect, paintRect);
		// canvas.drawRect(0, 0, mWidth, mHeight, paintRect);
	}

	private void drawFrame(Canvas canvas) {
		Log.v("dst",dstPs[0]+"===="+dstPs[1]+"====="+dstPs[2]+"===="+dstPs[3]);

		realDrawFrame(canvas, dstPs, paintFrame);
		if (drawDash) {
			drawDashFrame(canvas, dashPs, paintFrameDash);
		}
		// canvas.drawCircle(dstPs[8], dstPs[9], 10, paintFrame);
	}

	private void realDrawFrame(Canvas canvas, float[] points, Paint paint) {
		canvas.drawLine(points[0], points[1], points[2], points[3], paint);
		canvas.drawLine(points[2], points[3], points[4], points[5], paint);
		canvas.drawLine(points[4], points[5], points[6], points[7], paint);
		canvas.drawLine(points[6], points[7], points[0], points[1], paint);
	}

	private void drawDashFrame(Canvas canvas, float[] points, Paint paint) {
		Path path = new Path();
		path.moveTo(points[0], points[1]);
		path.lineTo(points[2], points[3]);
		path.lineTo(points[4], points[5]);
		path.lineTo(points[6], points[7]);
		path.lineTo(points[0], points[1]);
		canvas.drawPath(path, paint);
	}

	private void drawControlPoints(Canvas canvas) {
		canvas.drawBitmap(controlBmp, dashPs[cx] - controlBmpWidth / 2, dashPs[cy] - controlBmpHeight / 2, paint);
		canvas.drawBitmap(controlDelBmp, dashPs[6] - controlBmpWidth / 2, dashPs[7] - controlBmpHeight / 2, paint);
	}

	public interface OnRemoveListener {
		void onRemove();
	}

	public interface OnTipListener {
		void onTip();

		void hideAll(boolean first);
	}

	@Override
	public boolean onScale(ScaleGestureDetector arg0) {
		if (!scaleflag && arg0.getScaleFactor() != 1) {
			scaleflag = true;
			mOnTipListener.onTip();
		}
		return false;
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector arg0) {
		scaleflag = false;
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector arg0) {

	}

	public void setShowFlag(boolean isshow) {
		isShow = isshow;
	}

	private boolean isShow = true;

	public boolean isPasteMaskShow() {
		return isShow;
	}

//	public MYGifPoint getDXDY(View parentView) {
//		MYPaste paste= (MYPaste) getTag();
//		MYGifPoint  point=new MYGifPoint();
//		point.paste_id=paste.id;
//		point.paste_image_url=paste.image;
//		point.x_axis=(dstPs[0] / UIUtils.getScreenWidth()); // X坐标
//		point.y_axis=(dstPs[1] / UIUtils.getScreenWidth());// y坐标
//		point.z_axis=(dstPs[2]-dstPs[0]) / UIUtils.getScreenWidth();// z坐标-图片宽度
//
//		Log.v("tag:",point.x_axis+"");
//		Log.v("tag:",point.y_axis+"");
//		Log.v("tag:",point.z_axis+"");
//
//		return  point;
//	}

	public interface OnClickPasteViewListener{

		void onClickPasteArea();
		void onClickDelArea();
		void onClickControlArea();

	}

	private OnClickPasteViewListener mOnPasteViewListener;

	public void setOnContentClickListener(OnClickPasteViewListener pasteViewListener) {
		this.mOnPasteViewListener = pasteViewListener;
	}
}
