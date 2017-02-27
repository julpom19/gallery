package codewizards.com.ua.gallery.mvp;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

import codewizards.com.ua.gallery.util.Logger;

/**
 * Created by madless on 30.07.2016.
 */
public class PresenterCache {
    Logger logger = Logger.getLogger(PresenterCache.class);
    private static PresenterCache instance = null;

    private SimpleArrayMap<String, BasePresenter> presenters;

    private PresenterCache() {}

    public static PresenterCache get() {
        if (instance == null) {
            instance = new PresenterCache();
        }
        return instance;
    }

    @SuppressWarnings("unchecked") // Handled internally
    public final <T extends BasePresenter> T getPresenter(String who, PresenterFactory<T> presenterFactory) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }
        T p = null;
        try {
            p = (T) presenters.get(who);
        } catch (ClassCastException e) {
            Log.w("PresenterActivity", "Duplicate Presenter " +
                    "tag identified: " + who + ". This could " +
                    "cause issues with state.");
        }
        if (p == null) {
            p = presenterFactory.createPresenter();
            presenters.put(who, p);
        }
        return p;
    }

    public final void removePresenter(String who) {
        if (presenters != null) {
            BasePresenter presenter = presenters.remove(who);
            if(presenter != null) {
                logger.v("Presenter " + who + " has removed successfully!");
            } else {
                logger.v("Presenter " + who + " has not removed!");
            }
        }
    }

    public void print() {
        logger.i("=== Presenters in memory: ===");
        for (int i = 0; i < presenters.size(); i++) {
            String key = presenters.keyAt(i);
            logger.d("Presenter: " + key);
        }
    }

}
