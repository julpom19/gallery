package codewizards.com.ua.gallery.logic.db;

/**
 * Created by User on 28.02.2017.
 */

public interface DbEventObserver {
    void onDbUpdated(Object eventSrc);
}
