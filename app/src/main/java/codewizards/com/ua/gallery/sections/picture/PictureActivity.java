package codewizards.com.ua.gallery.sections.picture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import codewizards.com.ua.gallery.GalleryApp;
import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.managers.PreferencesManager;
import codewizards.com.ua.gallery.sections.abs.BaseActivity;
import codewizards.com.ua.gallery.util.Const;

/**
 * Created by User on 22.02.2017.
 */

public class PictureActivity extends BaseActivity {

    ImageView ivPicture;
    String picturePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processAppThemeChanging();
        setContentView(R.layout.activity_picture);
        ivPicture = (ImageView) findViewById(R.id.ivPicture);

        picturePath = getIntent().getStringExtra(Const.EXTRA_PHOTO_URL);

        Glide.with(this).load(picturePath).into(ivPicture);
    }

    @Override
    public void processAppThemeChanging() {
        String blue = GalleryApp.getAppContext().getString(R.string.settings_theme_value_blue);
        String green= GalleryApp.getAppContext().getString(R.string.settings_theme_value_green);
        String yellow = GalleryApp.getAppContext().getString(R.string.settings_theme_value_yellow);
        String s = PreferencesManager.getAppTheme();
        if (s.equals(blue)) {
            setTheme(R.style.FullScreenBlue);
        } else if (s.equals(green)) {
            setTheme(R.style.FullScreenGreen);
        } else if (s.equals(yellow)) {
            setTheme(R.style.FullScreenYellow);
        } else {
            setTheme(R.style.FullScreenBlue);
        }
    }
}
