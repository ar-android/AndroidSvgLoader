package com.ahmadrosid.svgloader;

import android.app.Activity;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.File;
import java.io.InputStream;
/**
 * Created by ocittwo on 3/19/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class SvgParser {

    private final Activity activity;

    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public SvgParser(Activity activity) {
        this.activity = activity;
        createRequestBuilder();
    }

    private void createRequestBuilder() {
        requestBuilder = Glide.with(activity)
                .using(Glide.buildStreamModelLoader(Uri.class, activity), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<>());
    }

    public void setPlaceHolder(int placeHolderLoding, int placeHolderError){
        requestBuilder.placeholder(placeHolderLoding)
                .error(placeHolderError);
    }


    public void loadImage(Uri uri, ImageView imageView) {
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(imageView);
    }

    public void clearCache() {
        Glide.get(activity).clearMemory();
        File cacheDir = Glide.getPhotoCacheDir(activity);
        if (cacheDir.isDirectory()) {
            for (File child : cacheDir.listFiles()) {
                if (!child.delete()) {
                    Log.w(TAG, "cannot delete: " + child);
                }
            }
        }
    }

    private static final String TAG = "SvgParser";
}
