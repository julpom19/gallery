package codewizards.com.ua.gallery.sections.gallery;

import android.content.Context;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import codewizards.com.ua.gallery.model.Image;
import codewizards.com.ua.gallery.util.StorageHelper;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByDate;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByDateReversed;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByName;
import codewizards.com.ua.gallery.util.comparators.ImageComparatorByNameReversed;

/**
 * Created by Интернет on 19.01.2017.
 */

public class GalleryFragmentPresenter {
    private static final String TAG = "GalleryFragmentPresent";
    private GalleryFragment view;
    private Context context;
    private List<Image> images;

    public void init(GalleryFragment view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void update() {
        Log.d(TAG, "update");
        images = StorageHelper.getAllImages(context);
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
}
