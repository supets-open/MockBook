package com.supets.pet.zxing.encoding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.supets.commons.App;
import com.supets.pet.mockui.R;


import java.util.Hashtable;

public final class EncodingHandler {

	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xffffffff;

	private static final int IMAGE_HALFWIDTH = 30;// 宽度值，影响中间图片大小

	public static Bitmap createQRCode(String str, int widthAndHeight)
			throws WriterException {

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, hints);

		matrix=updateBit(matrix, 5);


		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = BLACK;
				} else {
					pixels[y * width + x] = WHITE;
				}

			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	public static Bitmap createQRCodeWithLogo(String str, int widthAndHeight)
			throws WriterException {

     	Bitmap logo = BitmapFactory.decodeResource(App.INSTANCE.getResources(),
				R.drawable.icon);
		Matrix m = new Matrix();
		float sx = (float) 2 * IMAGE_HALFWIDTH / logo.getWidth();
		float sy = (float) 2 * IMAGE_HALFWIDTH / logo.getHeight();
		m.setScale(sx, sy);// 设置缩放信息
		// 将logo图片按martix设置的信息缩放
		logo = Bitmap.createBitmap(logo, 0, 0, logo.getWidth(),
				logo.getHeight(), m, false);

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight,hints);


		matrix=updateBit(matrix, 5);


		int width = matrix.getWidth();
		int height = matrix.getHeight();

		int halfW = width / 2;
		int halfH = height / 2;

		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
						&& y > halfH - IMAGE_HALFWIDTH
						&& y < halfH + IMAGE_HALFWIDTH) {
					// 次处位置用于存放图片信息
					pixels[y * width + x] = logo.getPixel(x - halfW
							+ IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);// 记录图片每个像素信息
				} else {

					if (matrix.get(x, y)) {
						pixels[y * width + x] = BLACK;
					} else {
						pixels[y * width + x] = WHITE;
					}
				}
			}
		}


		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	 private  static BitMatrix updateBit(BitMatrix matrix, int margin){
	        int tempM = margin*2;
	       int[] rec = matrix.getEnclosingRectangle();   //获取二维码图案的属性
	            int resWidth = rec[2] + tempM;
	            int resHeight = rec[3] + tempM;
	            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
	            resMatrix.clear();
	        for(int i= margin; i < resWidth- margin; i++){   //循环，将二维码图案绘制到新的bitMatrix中
	            for(int j=margin; j < resHeight-margin; j++){
	                if(matrix.get(i-margin + rec[0], j-margin + rec[1])){
	                    resMatrix.set(i,j);
	                }
	            }
	        }
	         return resMatrix;
	    }

}
