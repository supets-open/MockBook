package com.supets.pet.mock.ui.crop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.supets.commons.utils.file.ImageUtils;
import com.supets.pet.mock.utils.FileUtils;
import com.supets.pet.mockui.R;

import java.io.File;
import java.io.FileOutputStream;

public class MainCrop2Activity extends AppCompatActivity {


    private ClipImageLayout mCropImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop2);
        mCropImageView = findViewById(R.id.clipImageLayout);
        mCropImageView.setHorizontalPadding(20);


        mCropImageView.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.taeyeon_three));

        final ImageView btn = findViewById(R.id.CropImageView);
        findViewById(R.id.Crop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setImageBitmap(ClipPicByCache());
            }
        });
        findViewById(R.id.rotate90).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCropImageView.rotateImage(-90);
            }
        });

    }


    public Bitmap ClipPicByCache() {
        try {
            mCropImageView.getZoomImageView().setDrawingCacheEnabled(true);
            mCropImageView.getZoomImageView().buildDrawingCache();
            Bitmap bit = mCropImageView.getZoomImageView().getDrawingCache();
            Rect rect = mCropImageView.getZoomImageView().getClipRect();
            Bitmap bitmap2 = Bitmap.createBitmap(bit, rect.left, rect.top, rect.right, rect.bottom);
            mCropImageView.getZoomImageView().setDrawingCacheEnabled(false);
            if (bit != null) {
                bit.recycle();
            }

            return bitmap2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}