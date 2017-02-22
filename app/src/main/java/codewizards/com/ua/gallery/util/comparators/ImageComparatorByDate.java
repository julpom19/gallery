package codewizards.com.ua.gallery.util.comparators;

import java.util.Comparator;

import codewizards.com.ua.gallery.model.Image;

/**
 * Created by User on 22.02.2017.
 */

public class ImageComparatorByDate implements Comparator<Image> {
    @Override
    public int compare(Image i1, Image i2) {
        long d1 = i1.getDate();
        long d2 = i2.getDate();
        return d1 == d2 ? 0 : d1 > d2 ? 1 : -1;
    }
}
