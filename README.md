# AndroidSvgLoader
Android library for load svg from internet to imageview


## Installation
**build.gradle**
```gradle
allprojects {
  repositories {
   maven { url 'https://jitpack.io' }
  }
 }
```
**app/build.grdle**
```gradle
implementation 'com.github.ar-android:AndroidSvgLoader:1.0.2'
```

## or 
```gradle
compile 'com.github.ar-android:AndroidSvgLoader:1.0.2'
```

## Usage
```java
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

    /**
     * Use this if you need to load svg from a specific directory
     */
    private void loadFromUri(){
        Uri uri = Uri.parse("android.resource://com.ahmadrosid.androidsvgloader/" + R.raw.sample);
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
```

## License

Licensed under the Apache license 2.0.
