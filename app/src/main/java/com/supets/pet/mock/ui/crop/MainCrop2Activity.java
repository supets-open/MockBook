package com.supets.pet.mock.ui.crop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

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

        mCropImageView.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.taeyeon_three));
        mCropImageView.setHorizontalPadding(30,30);

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
                mCropImageView.getZoomImageView().setRotate(-90);
            }
        });
        findViewById(R.id.flipv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCropImageView.getZoomImageView().flipVertical();
            }
        });
        findViewById(R.id.fliph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCropImageView.getZoomImageView().flipHorizontal();
            }
        });

        final SeekBar seekBarx = findViewById(R.id.aspectRatioX);
        final SeekBar seekBary = findViewById(R.id.aspectRatioY);
        seekBarx.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCropImageView.setAspectRatio(progress, seekBary.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCropImageView.setAspectRatio(seekBarx.getProgress(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public Bitmap ClipPicByCache() {
        try {
            mCropImageView.getZoomImageView().setDrawingCacheEnabled(true);
            mCropImageView.getZoomImageView().buildDrawingCache();
            Bitmap bit = mCropImageView.getZoomImageView().getDrawingCache();
            Rect rect = mCropImageView.getZoomImageView().getClipRect();
            Bitmap bitmap2 = Bitmap.createBitmap(bit, rect.left, rect.top, rect.width(), rect.height());
            mCropImageView.getZoomImageView().setDrawingCacheEnabled(false);
            bit.recycle();
            return bitmap2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}