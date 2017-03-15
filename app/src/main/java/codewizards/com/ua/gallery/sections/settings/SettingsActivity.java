package codewizards.com.ua.gallery.sections.settings;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.managers.PreferencesManager;
import codewizards.com.ua.gallery.util.Logger;

/**
 * Created by User on 15.03.2017.
 */

public class SettingsActivity extends PreferenceActivity {
    Logger logger = Logger.getLogger(this.getClass());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference("app_sound");
        checkBoxPreference.setChecked(PreferencesManager.isSoundFxEnabled());
        checkBoxPreference.setSummary(PreferencesManager.isSoundFxEnabled() ? "Sound FX enabled" : "Sound FX disabled");
        checkBoxPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            PreferencesManager.setSoundFxEnabled((Boolean) newValue);
            checkBoxPreference.setSummary((Boolean) newValue ? "Sound FX enabled" : "Sound FX disabled");
            return true;
        });
        ListPreference listPreference = (ListPreference) findPreference("app_theme");
        listPreference.setValueIndex(0);
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                logger.d("on list pref change new value: " + newValue);
                return true;
            }
        });
    }
}
