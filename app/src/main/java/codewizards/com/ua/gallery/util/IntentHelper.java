package codewizards.com.ua.gallery.util;

import android.app.Activity;
import android.content.Intent;

import codewizards.com.ua.gallery.sections.picture.PictureActivity;

/**
 * Created by User on 22.02.2017.
 */

public class IntentHelper {
    public static void openPictureActivity(Activity context, String imgPath) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(Const.EXTRA_PHOTO_URL, imgPath);
        context.startActivity(intent);
    }
}
