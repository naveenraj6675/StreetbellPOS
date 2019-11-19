package com.example.streetbellpos.helpers.transform;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;


public class SquareCropTransformation extends BitmapTransformation {

    private int size;

    public SquareCropTransformation(Context context) {
        super();
    }


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        size = Math.min(toTransform.getWidth(), toTransform.getHeight());

        int mWidth = (toTransform.getWidth() - size) / 2;
        int mHeight = (toTransform.getHeight() - size) / 2;

        Bitmap bitmap = Bitmap.createBitmap(toTransform, mWidth, mHeight, size, size);

        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
