package codewizards.com.ua.gallery.sections.gallery;

import android.content.Context;

import java.util.Collections;
import java.util.Comparator;
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
    private Comparator<GalleryImage> comparator = new ImageComparatorByDateReversed();

    public void init(GalleryFragment view, Context context) {
        logger.d("init");
        this.view = view;
        this.context = context;
    }

    public void start() {
        if(images != null) {
            Collections.sort(images, comparator);
            view.onImagesUpdated(images);
        } else {
            repository.addObserver(this);
            fetchFreshData();
        }
    }

    public void update() {
        repository.addObserver(this);
        fetchFreshData();
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
        Collections.sort(images, comparator);
        view.onImagesUpdated(images);
    }

    public void sortByDate() {
        comparator = new ImageComparatorByDate();
        Collections.sort(images, comparator);
        view.onImagesUpdated(images);
    }

    public void sortByName() {
        comparator = new ImageComparatorByName();
        Collections.sort(images, comparator);
        view.onImagesUpdated(images);
    }

    public void sortByDateReversed() {
        comparator = new ImageComparatorByDateReversed();
        Collections.sort(images, comparator);
        view.onImagesUpdated(images);
    }

    public void sortByNameReversed() {
        comparator = new ImageComparatorByNameReversed();
        Collections.sort(images, comparator);
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
