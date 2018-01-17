package com.supets.pet.mock.ui.crop;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.supets.pet.mockui.R;

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

    }
}