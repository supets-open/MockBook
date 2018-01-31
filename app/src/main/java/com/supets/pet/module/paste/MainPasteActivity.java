package com.supets.pet.module.paste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.supets.commons.utils.UIUtils;
import com.supets.pet.mockui.R;

public class MainPasteActivity extends AppCompatActivity {


    private ViewGroup mPadterLayer;
    private ImageView result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paste);
        mPadterLayer = findViewById(R.id.pasteLayout);
        addPasterView(BitmapFactory.decodeResource(getResources(), R.drawable.taeyeon_one));
        addPasterView(BitmapFactory.decodeResource(getResources(), R.drawable.icon_big));

        result = findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergePaste();
            }
        });
    }

    public void addPasterView(Bitmap bitmap) {
        final XGPasterView xgPasterView = new XGPasterView(this);
        xgPasterView.setPaster(bitmap, UIUtils.getScreenWidth(), UIUtils.getScreenWidth(), 10, mPadterLayer.getChildCount() + 1, 0.4f);
        //xgPasterView.setTag(paste);
        xgPasterView.setOnRemoveListener(new XGPasterView.OnRemoveListener() {

            @Override
            public void onRemove() {
                // MYPaste paste1 = (MYPaste) xgPasterView.getTag();
                mPadterLayer.removeView(xgPasterView);
//                if (!isExistMYPaste(paste1)) {
//                    paste1.isSelected = false;
//                    mPasterAdapter.updateStatus(paste1);
//                }
            }

        });
        xgPasterView.setOnContentClickListener(new XGPasterView.OnClickPasteViewListener() {
            @Override
            public void onClickPasteArea() {
                if (xgPasterView.isUsed()) {
                    boolean isTopView = xgPasterView == mPadterLayer.getChildAt(mPadterLayer.getChildCount() - 1);

                    if (isTopView) {

                        if (!xgPasterView.isPasteMaskShow()) {
                            xgPasterView.setShowFlag(true);
                            xgPasterView.invalidate();
                            return;
                        }
                    } else {
                        xgPasterView.bringToFront();
                    }
                }
            }

            @Override
            public void onClickDelArea() {
                //MYPaste paste1 = (MYPaste) xgPasterView.getTag();
                mPadterLayer.removeView(xgPasterView);
//                if (!isExistMYPaste(paste1)) {
//                    paste1.isSelected = false;
//                    mPasterAdapter.updateStatus(paste1);
//                }
            }

            @Override
            public void onClickControlArea() {

            }
        });
        xgPasterView.setOnTipListener(new XGPasterView.OnTipListener() {

            @Override
            public void onTip() {
                //MYUtils.showToastMessage(R.string.paste_scale_tip);
            }

            @Override
            public void hideAll(boolean first) {
                if (first) {
                    for (int i = 0; i < mPadterLayer.getChildCount(); i++) {
                        XGPasterView view = (XGPasterView) mPadterLayer.getChildAt(i);
                        if (view != null) {
                            view.setShowFlag(false);
                            view.invalidate();
                        }
                    }
                }
            }
        });
        mPadterLayer.addView(xgPasterView);
    }


    public void mergePaste() {

        try {

            Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.taeyeon_one);

            Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();

            canvas.drawBitmap(src, 0, 0, paint);
            for (int i = 0; i < mPadterLayer.getChildCount(); i++) {
                XGPasterView xgPasterView = (XGPasterView) mPadterLayer.getChildAt(i);
                Bitmap pasterBitmap = xgPasterView.getPasterBitmap();
                Matrix matrix = xgPasterView.getPasterMatrix(src);
                canvas.drawBitmap(pasterBitmap, matrix, paint);
            }

            result.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}