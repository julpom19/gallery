package codewizards.com.ua.gallery.sections;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.sections.abs.BaseActivity;
import codewizards.com.ua.gallery.sections.images.ImagesFragment;
import codewizards.com.ua.gallery.sections.photo.PhotoFragment;
import codewizards.com.ua.gallery.util.Const;
import codewizards.com.ua.gallery.util.DialogHelper;
import codewizards.com.ua.gallery.util.IntentHelper;

public class MainActivity extends BaseActivity {

    FrameLayout container;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout) findViewById(R.id.container);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolbar();
        initNavigationDrawer();

        checkPermissions();
    }

    public void init() {
        ImagesFragment fragment = new ImagesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initNavigationDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_item_images: {
                    fragment = new ImagesFragment();
                    break;
                }
                case R.id.navigation_item_photo: {
                    fragment = new PhotoFragment();
                    break;
                }
                case R.id.navigation_item_settings: {
                    IntentHelper.openSettingsActivity(this);
                    break;
                }
                case R.id.navigation_item_exit: {
                    finish();
                    return true;
                }
            }
            if(fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(fragment.getClass().toString())
                        .commit();
            }
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            init();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Const.READ_WRITE_TO_STORAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Const.READ_WRITE_TO_STORAGE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init();
                } else {
                    DialogHelper.openMessageDialog(this, "Error", "Please enable permissions to use this app");
                    checkPermissions();
                }
            }
        }
    }
}
