package codewizards.com.ua.gallery.sections.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.TimerTask;

import codewizards.com.ua.gallery.GalleryApp;
import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.managers.PreferencesManager;
import codewizards.com.ua.gallery.managers.SoundManager;
import codewizards.com.ua.gallery.sections.abs.BaseActivity;
import codewizards.com.ua.gallery.util.IntentHelper;

/**
 * Created by User on 15.03.2017.
 */

public class SplashActivity extends BaseActivity {

    private final static int FULL_CIRCLE_ANGLE = 360;
    private final static int ANIM_DELAY = 200;
    private final static int ANIM_DURATION = 700;

    private ImageView ivHourglass;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivHourglass = (ImageView) findViewById(R.id.ivHourglass);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        processAppThemeChanging();
        SoundManager.get().playSplashSound();
        ivHourglass.animate().rotationBy(FULL_CIRCLE_ANGLE).setStartDelay(ANIM_DELAY).withEndAction(new TimerTask() {
            @Override
            public void run() {
                ivHourglass.animate().rotationBy(FULL_CIRCLE_ANGLE).setStartDelay(ANIM_DELAY).withEndAction(new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                        IntentHelper.openMainActivity(SplashActivity.this);
                    }
                }).setDuration(ANIM_DURATION);
            }
        }).setDuration(ANIM_DURATION);
    }

    @Override
    public void processAppThemeChanging() {
        String blue = GalleryApp.getAppContext().getString(R.string.settings_theme_value_blue);
        String green= GalleryApp.getAppContext().getString(R.string.settings_theme_value_green);
        String yellow = GalleryApp.getAppContext().getString(R.string.settings_theme_value_yellow);
        String s = PreferencesManager.getAppTheme();
        if (s.equals(blue)) {
            GalleryApp.setAppTheme(R.style.AppThemeBlue);
            relativeLayout.setBackground(ContextCompat.getDrawable(SplashActivity.this, R.drawable.bg_splash_blue));
        } else if (s.equals(green)) {
            GalleryApp.setAppTheme(R.style.AppThemeGreen);
            relativeLayout.setBackground(ContextCompat.getDrawable(SplashActivity.this, R.drawable.bg_splash_green));
        } else if (s.equals(yellow)) {
            GalleryApp.setAppTheme(R.style.AppThemeYellow);
            relativeLayout.setBackground(ContextCompat.getDrawable(SplashActivity.this, R.drawable.bg_splash_yellow));
        } else {
            GalleryApp.setAppTheme(R.style.AppThemeBlue);
            relativeLayout.setBackground(ContextCompat.getDrawable(SplashActivity.this, R.drawable.bg_splash_blue));
        }
    }
}
