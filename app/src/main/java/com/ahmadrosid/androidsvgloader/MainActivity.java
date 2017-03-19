package com.ahmadrosid.androidsvgloader;

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

        url = "http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg";

        image = (ImageView) findViewById(R.id.image);

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(url, image);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
}
