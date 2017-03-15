package codewizards.com.ua.gallery.util;

import android.app.Activity;
import android.content.Intent;

import codewizards.com.ua.gallery.sections.MainActivity;
import codewizards.com.ua.gallery.sections.picture.PictureActivity;
import codewizards.com.ua.gallery.sections.settings.SettingsActivity;

/**
 * Created by User on 22.02.2017.
 */

public class IntentHelper {

    public static void openPictureActivity(Activity context, String imgPath) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(Const.EXTRA_PHOTO_URL, imgPath);
        context.startActivity(intent);
    }

    public static void openMainActivity(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void openSettingsActivity(Activity context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }
}
