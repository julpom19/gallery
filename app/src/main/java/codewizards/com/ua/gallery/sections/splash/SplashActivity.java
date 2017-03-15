package codewizards.com.ua.gallery.sections.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.util.TimerTask;

import codewizards.com.ua.gallery.R;
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

    ImageView ivHourglass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivHourglass = (ImageView) findViewById(R.id.ivHourglass);
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
}
