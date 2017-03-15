package codewizards.com.ua.gallery.sections.settings;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

import codewizards.com.ua.gallery.GalleryApp;
import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.managers.PreferencesManager;
import codewizards.com.ua.gallery.util.DialogHelper;
import codewizards.com.ua.gallery.util.IntentHelper;
import codewizards.com.ua.gallery.util.Logger;

/**
 * Created by User on 15.03.2017.
 */

public class SettingsActivity extends PreferenceActivity {
    Logger logger = Logger.getLogger(this.getClass());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processAppThemeChanging();
        addPreferencesFromResource(R.xml.pref);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference("app_sound");
        checkBoxPreference.setChecked(PreferencesManager.isSoundFxEnabled());
        checkBoxPreference.setSummary(PreferencesManager.isSoundFxEnabled() ? getString(R.string.settings_sound_enabled) : getString(R.string.settings_sound_disabled));
        checkBoxPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            PreferencesManager.setSoundFxEnabled((Boolean) newValue);
            checkBoxPreference.setSummary((Boolean) newValue ? getString(R.string.settings_sound_enabled) : getString(R.string.settings_sound_disabled));
            return true;
        });
        ListPreference listPreference = (ListPreference) findPreference("app_theme");
        listPreference.setValue(PreferencesManager.getAppTheme());
        listPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            logger.d("on list pref change new value: " + newValue);
            String title = getString(R.string.settings_theme_changing_title);
            String msg = getString(R.string.settings_theme_changing_msg);
            String confirmButton = getString(R.string.settings_theme_changing_restart);
            DialogHelper.openConfirmationDialog(SettingsActivity.this, title, msg, confirmButton,
                    (dialog, which) -> IntentHelper.restartApplication(SettingsActivity.this));
            return true;
        });
    }

    public void processAppThemeChanging() {
        String blue = GalleryApp.getAppContext().getString(R.string.settings_theme_value_blue);
        String green= GalleryApp.getAppContext().getString(R.string.settings_theme_value_green);
        String yellow = GalleryApp.getAppContext().getString(R.string.settings_theme_value_yellow);
        String s = PreferencesManager.getAppTheme();
        if (s.equals(blue)) {
            setTheme(R.style.AppThemeBlue);
        } else if (s.equals(green)) {
            setTheme(R.style.AppThemeGreen);
        } else if (s.equals(yellow)) {
            setTheme(R.style.AppThemeYellow);
        } else {
            setTheme(R.style.AppThemeBlue);
        }
    }
}
