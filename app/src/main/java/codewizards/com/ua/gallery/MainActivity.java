package codewizards.com.ua.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import codewizards.com.ua.gallery.ui.fragments.gallery.GalleryFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout) findViewById(R.id.container);
        GalleryFragment galleryFragment = new GalleryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, galleryFragment).commit();
//        for(String url : urls) {
//            Log.d(TAG, "url: " + url);
//        }
//        Log.d(TAG, "onCreate: here");
    }
}
