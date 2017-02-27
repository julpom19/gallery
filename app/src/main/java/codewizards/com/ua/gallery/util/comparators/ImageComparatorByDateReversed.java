package codewizards.com.ua.gallery.util.comparators;

import java.util.Comparator;

import codewizards.com.ua.gallery.model.ui.GalleryImage;

/**
 * Created by User on 23.02.2017.
 */

public class ImageComparatorByDateReversed implements Comparator<GalleryImage> {
    @Override
    public int compare(GalleryImage i1, GalleryImage i2) {
        long d1 = i1.getDate();
        long d2 = i2.getDate();
        return d1 == d2 ? 0 : d1 > d2 ? -1 : 1;
    }
}
