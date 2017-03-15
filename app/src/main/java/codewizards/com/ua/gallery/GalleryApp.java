package codewizards.com.ua.gallery;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by User on 27.02.2017.
 */

public class GalleryApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Stetho.initializeWithDefaults(this);
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }
    public static Context getAppContext() {
        return context;
    }

    public static void setAppTheme(int themeId) {
        context.setTheme(themeId);
    }
}
