package com.ahmadrosid.androidsvgloader;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ahmadrosid.svgloader.SvgLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);

        loadFromUri();
    }

    private void loadFromUrl(){
        url = "http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg";
        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(url, image);
    }

    /**
     * Use this if you need to load svg from a specific directory
     */
    private void loadFromUri(){
        url = "android.resource://com.ahmadrosid.androidsvgloader/" + R.raw.sample;
        Uri uri = Uri.parse(url);
        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(uri, image);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
}
