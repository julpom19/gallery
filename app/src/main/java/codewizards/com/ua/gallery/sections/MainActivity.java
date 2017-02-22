package codewizards.com.ua.gallery.sections;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.sections.gallery.GalleryFragment;
import codewizards.com.ua.gallery.util.Const;
import codewizards.com.ua.gallery.util.DialogHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout) findViewById(R.id.container);
        checkPermissions();
    }

    public void openGalleryFragment() {
        GalleryFragment galleryFragment = new GalleryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, galleryFragment).commitAllowingStateLoss();
    }

    public void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.d(TAG, "checkPermissions: permissionCheck == PackageManager.PERMISSION_GRANTED: " + (permissionCheck == PackageManager.PERMISSION_GRANTED));
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            openGalleryFragment();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Const.READ_WRITE_TO_STORAGE_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Const.READ_WRITE_TO_STORAGE_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requestCode: " + requestCode);
        switch (requestCode) {
            case Const.READ_WRITE_TO_STORAGE_PERMISSION: {
                Log.d(TAG, "onRequestPermissionsResult: permissions[0]: " + permissions[0]);
                Log.d(TAG, "onRequestPermissionsResult: grantResults[0]: " + grantResults[0]);
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGalleryFragment();
                } else {
                    DialogHelper.openMessageDialog(this, "Error", "Please enable permissions to use this app");
                }
            }
        }
    }
}
