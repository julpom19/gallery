package codewizards.com.ua.gallery.sections.gallery;

import android.content.Context;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import codewizards.com.ua.gallery.logic.db.DbEventObserver;
import codewizards.com.ua.gallery.logic.db.DbManager;
import codewizards.com.ua.gallery.model.ui.GalleryImage;
import codewizards.com.ua.gallery.mvp.BasePresenter;
import codewizards.com.ua.gallery.util.Logger;
import codewizards.com.ua.gallery.util.StorageHelper;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByDate;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByDateReversed;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByName;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByNameReversed;

/**
 * Created by Интернет on 19.01.2017.
 */

public class GalleryFragmentPresenter extends BasePresenter implements DbEventObserver {

    Logger logger = Logger.getLogger(this.getClass());

    private GalleryFragment view;
    private Context context;
    private List<GalleryImage> images;
    private DbManager repository = DbManager.get();

    public void init(GalleryFragment view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void update() {
        logger.d("update");
        if(images != null) {
            view.onImagesUpdated(images);
        } else {
            repository.addObserver(this);
            fetchFreshData();
        }
    }

    public void fetchFreshData() {
        images = StorageHelper.getAllImages(context);
        Set<GalleryImage> favoriteImagesSet = new HashSet<>(repository.getFavoriteImages());
        Set<GalleryImage> imagesSet = new HashSet<>(images);
        logger.d("favoriteImagesSet: " + favoriteImagesSet);
        logger.d("imagesSet: " + imagesSet);
        imagesSet.retainAll(favoriteImagesSet);
        for (GalleryImage image: imagesSet) {
            logger.d("favorite image: " + image);
            image.setFavorite(true);
        }
        view.onImagesUpdated(images);
    }

    public void sortByDate() {
        Collections.sort(images, new ImageComparatorByDate());
        view.onImagesUpdated(images);
    }

    public void sortByName() {
        Collections.sort(images, new ImageComparatorByName());
        view.onImagesUpdated(images);
    }

    public void sortByDateReversed() {
        Collections.sort(images, new ImageComparatorByDateReversed());
        view.onImagesUpdated(images);
    }

    public void sortByNameReversed() {
        Collections.sort(images, new ImageComparatorByNameReversed());
        view.onImagesUpdated(images);
    }

    public void addToFavorite(GalleryImage image) {
        repository.addImageToFavorite(this, image);
    }

    public void removeFromFavorites(GalleryImage image) {
        repository.removeImageFromFavorites(this, image);
    }

    @Override
    public void onDbUpdated(Object eventSrc) {
        if(!eventSrc.equals(this)) {
            fetchFreshData();
        }
    }

    public void onDestroy() {
        repository.removeObserver(this);
    }
}
