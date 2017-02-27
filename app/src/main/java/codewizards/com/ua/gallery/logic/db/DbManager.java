package codewizards.com.ua.gallery.logic.db;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import codewizards.com.ua.gallery.model.db.RealmImage;
import codewizards.com.ua.gallery.model.ui.GalleryImage;
import codewizards.com.ua.gallery.util.Logger;
import codewizards.com.ua.gallery.util.converters.RealmConverterImage;
import io.realm.Realm;

/**
 * Created by User on 27.02.2017.
 */

public class DbManager {

    private Logger logger = Logger.getLogger(this.getClass());

    private static final String REALM_IMAGE_FIELD_URL = "url";
    private static final String REALM_IMAGE_FIELD_FAVORITE = "isFavorite";

    private static DbManager instance = new DbManager();

    private RealmConverterImage converterImage = new RealmConverterImage();

    private Set<DbEventObserver> observers = new HashSet<>();

    private DbManager() {}

    public static DbManager get() {
        return instance;
    }

    // ************ OBSERVABLE METHODS **************** //

    public void addObserver(DbEventObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DbEventObserver observer) {
        observers.remove(observer);
    }

    public void notifyObserversOnDbUpdatedEvent(Object eventSrc) {
        for (DbEventObserver o: observers) {
            o.onDbUpdated(eventSrc);
        }
    }

    // *************** DB METHODS ************** //

    public List<GalleryImage> getFavoriteImages() {
        Realm realm = Realm.getDefaultInstance();
        List<RealmImage> realmImages = realm.where(RealmImage.class).equalTo(REALM_IMAGE_FIELD_FAVORITE, true).findAll();
        List<GalleryImage> images = converterImage.convertToUi(realmImages);
        return images;
    }

    public void addImageToFavorite(Object eventSrc, GalleryImage image) {
        logger.d("add image to favorites: " + image);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            RealmImage realmImage = converterImage.convertFromUi(image);
            r.copyToRealm(realmImage);
            notifyObserversOnDbUpdatedEvent(eventSrc);
        });
    }

    public void removeImageFromFavorites(Object eventSrc, GalleryImage image) {
        logger.d("remove image from favorites: " + image);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            RealmImage realmImage = r.where(RealmImage.class).equalTo(REALM_IMAGE_FIELD_URL, image.getUrl()).findFirst();
            logger.d("realm image: " + realmImage);
            if(realmImage != null) {
                realmImage.removeFromRealm();
                notifyObserversOnDbUpdatedEvent(eventSrc);
            }
        });
    }

}
