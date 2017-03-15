package codewizards.com.ua.gallery.sections.abs;

import android.support.v7.app.AppCompatActivity;

import codewizards.com.ua.gallery.util.Logger;

/**
 * Created by User on 15.03.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Logger logger = Logger.getLogger(this.getClass());

    public abstract void processAppThemeChanging();
}
