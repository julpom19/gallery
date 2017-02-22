package codewizards.com.ua.gallery.util.comparators;

import java.util.Comparator;

import codewizards.com.ua.gallery.model.Image;

/**
 * Created by User on 23.02.2017.
 */

public class ImageComparatorByNameReversed implements Comparator<Image> {
    @Override
    public int compare(Image i1, Image i2) {
        String n1 = i1.getName();
        String n2 = i2.getName();
        if(n1 == null && n2 == null) {
            return 0;
        } else if(n1 == null) {
            return 1;
        } else if(n2 == null) {
            return -1;
        } else {
            return -n1.compareTo(n2);
        }
    }
}
