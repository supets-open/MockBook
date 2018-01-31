package com.supets.pet.module.zxing;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;

import android.os.AsyncTask;

import android.os.Bundle;

import android.os.Vibrator;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import android.text.TextUtils;

import android.util.Log;

import android.view.View;

import android.widget.Toast;

import com.supets.pet.mockui.R;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;


public class TestScanActivity extends AppCompatActivity implements QRCodeView.Delegate {

    private static final String TAG = TestScanActivity.class.getSimpleName();

    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    private QRCodeView mQRCodeView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scan);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mQRCodeView = findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);

    }


    @Override

    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }


    @Override

    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }


    @Override

    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }


    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }


    @Override

    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();

        mQRCodeView.startSpot();

    }


    @Override

    public void onScanQRCodeOpenCameraError() {

        Log.e(TAG, "打开相机出错");

    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start_spot:

                mQRCodeView.startSpot();

                break;

            case R.id.stop_spot:

                mQRCodeView.stopSpot();

                break;

            case R.id.start_spot_showrect:

                mQRCodeView.startSpotAndShowRect();

                break;

            case R.id.stop_spot_hiddenrect:

                mQRCodeView.stopSpotAndHiddenRect();

                break;

            case R.id.show_rect:

                mQRCodeView.showScanRect();

                break;

            case R.id.hidden_rect:

                mQRCodeView.hiddenScanRect();

                break;

            case R.id.start_preview:

                mQRCodeView.startCamera();

                break;

            case R.id.stop_preview:

                mQRCodeView.stopCamera();

                break;

            case R.id.open_flashlight:

                mQRCodeView.openFlashlight();

                break;

            case R.id.close_flashlight:

                mQRCodeView.closeFlashlight();

                break;

            case R.id.scan_barcode:

                mQRCodeView.changeToScanBarcodeStyle();

                break;

            case R.id.scan_qrcode:

                mQRCodeView.changeToScanQRCodeStyle();

                break;

            case R.id.choose_qrcde_from_gallery:

                /*

                从相册选取二维码图片，这里为了方便演示，使用的是

                https://github.com/bingoogolapple/BGAPhotoPicker-Android

                这个库来从图库中选择二维码图片，这个库不是必须的，你也可以通过自己的方式从图库中选择图片

                 */

                startActivityForResult(BGAPhotoPickerActivity.newIntent(this, null, 1, null), REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);

                break;

        }

    }


    @SuppressLint("StaticFieldLeak")
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        mQRCodeView.showScanRect();


        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {

            final String picturePath = BGAPhotoPickerActivity.getSelectedImages(data).get(0);

            new AsyncTask<Void, Void, String>() {

                @Override

                protected String doInBackground(Void... params) {

                    return QRCodeDecoder.syncDecodeQRCode(picturePath);

                }


                @Override

                protected void onPostExecute(String result) {

                    if (TextUtils.isEmpty(result)) {

                        Toast.makeText(TestScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(TestScanActivity.this, result, Toast.LENGTH_SHORT).show();

                    }

                }

            }.execute();

        }

    }


}