package codewizards.com.ua.gallery.managers;

import android.content.Context;
import android.content.SharedPreferences;

import codewizards.com.ua.gallery.GalleryApp;
import codewizards.com.ua.gallery.util.Logger;

/**
 * Created by User on 15.03.2017.
 */

public class PreferencesManager {
    Logger logger = Logger.getLogger(this.getClass());
    // ********** GROUPS KEYS ********** //
    private static final String PREFS_GROUP_AUDIO_SETTINGS = "PREFS_GROUP_AUDIO_SETTINGS";

    // ********** VALUES KEYS ********** //
    private static final String PREFS_SOUND_FX_ENABLED = "PREFS_SOUND_FX_ENABLED";

    private static SharedPreferences getSharedPreferences(String name) {
        return GalleryApp.getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void setSoundFxEnabled(boolean isSoundFxEnabled) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_GROUP_AUDIO_SETTINGS).edit().putBoolean(PREFS_SOUND_FX_ENABLED, isSoundFxEnabled);
        editor.apply();
    }

    public static boolean isSoundFxEnabled() {
        return getSharedPreferences(PREFS_GROUP_AUDIO_SETTINGS).getBoolean(PREFS_SOUND_FX_ENABLED, true);
    }
}
