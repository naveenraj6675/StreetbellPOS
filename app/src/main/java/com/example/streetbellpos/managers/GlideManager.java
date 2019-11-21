package com.example.streetbellpos.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.streetbellpos.helpers.transform.CircleTransform;
import com.example.streetbellpos.helpers.transform.RoundedCornersTransformation;
import com.example.streetbellpos.helpers.transform.SquareCropTransformation;
import com.example.streetbellpos.managers.glide.GlideApp;
import com.example.streetbellpos.options.GLIDE_TRANSFORM_OPTION;


public class GlideManager {

    public static void loadImage(Context context, ImageView imageView, String url, @GLIDE_TRANSFORM_OPTION int glideTransformOption) {
        loadImage(context, imageView, url, 0, glideTransformOption);
    }

    public static void loadImage(Context context, ImageView imageView, String url, int placeHolder, @GLIDE_TRANSFORM_OPTION int glideTransformOption) {

        switch (glideTransformOption) {
            case GLIDE_TRANSFORM_OPTION.NO_TRANSFORM:
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                break;
            case GLIDE_TRANSFORM_OPTION.CIRCLE_TRANSFORM_NO_BORDER:
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).apply(new RequestOptions().circleCrop()).into(imageView);

//                GlideApp.with(context).load(url).placeholder(placeHolder).transform(new CircleTransform(context, glideTransformOption)).into(imageView);
                break;
            case GLIDE_TRANSFORM_OPTION.CIRCLE_TRANSFORM_WHITE_BORDER:
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).apply(new RequestOptions().circleCrop()).into(imageView);
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new CircleTransform(context, glideTransformOption)).into(imageView);
                break;
            case GLIDE_TRANSFORM_OPTION.ROUNDER_CORNER_TRANSFORM:
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new RoundedCornersTransformation(context)).into(imageView);
                break;
            case GLIDE_TRANSFORM_OPTION.SQUARE_CROP_TRANFRORM:
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new SquareCropTransformation(context)).into(imageView);
                break;
            case GLIDE_TRANSFORM_OPTION.CIRCLE_TRANSFORM_BLACK_BORDER:
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new CircleTransform(context, glideTransformOption)).into(imageView);
                break;
            case GLIDE_TRANSFORM_OPTION.SQUARE_ROUNDED_CORNER_TRANSFORM:
                GlideApp.with(context).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new MultiTransformation<Bitmap>(new SquareCropTransformation(context), new RoundedCornersTransformation(context))).into(imageView);
                break;
            default:
                GlideApp.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                break;
        }
    }

    public static void loadImage(Context context, ImageView imageView, int image) {
        GlideApp.with(context).load(image).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

    }
}
