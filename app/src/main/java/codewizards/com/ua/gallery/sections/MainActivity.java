package codewizards.com.ua.gallery.sections;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.sections.abs.BaseFragment;
import codewizards.com.ua.gallery.sections.favorites.FavoritesFragment;
import codewizards.com.ua.gallery.sections.gallery.GalleryFragment;
import codewizards.com.ua.gallery.sections.internet.InternetFragment;
import codewizards.com.ua.gallery.sections.ui.FragmentGalleryPagerAdapter;
import codewizards.com.ua.gallery.util.Const;
import codewizards.com.ua.gallery.util.DialogHelper;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private FragmentGalleryPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        checkPermissions();
    }

    public void initFragments() {
        GalleryFragment galleryFragment = new GalleryFragment();
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        InternetFragment internetFragment = new InternetFragment();
        List<BaseFragment> fragments = new ArrayList<>(Arrays.asList(galleryFragment, favoritesFragment, internetFragment));
        List<String> titles = new ArrayList<>(Arrays.asList("Gallery", "Favorites", "Google search"));
        adapter = new FragmentGalleryPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            initFragments();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Const.READ_WRITE_TO_STORAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Const.READ_WRITE_TO_STORAGE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initFragments();
                } else {
                    DialogHelper.openMessageDialog(this, "Error", "Please enable permissions to use this app");
                    checkPermissions();
                }
            }
        }
    }
}
