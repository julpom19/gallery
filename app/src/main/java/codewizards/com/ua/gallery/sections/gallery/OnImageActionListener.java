package codewizards.com.ua.gallery.sections.gallery;

import codewizards.com.ua.gallery.model.ui.GalleryImage;

/**
 * Created by User on 22.02.2017.
 */

public interface OnImageActionListener {
    void onImageSelected(GalleryImage image);
    void onImageFavorite(GalleryImage image);
    void onImageUnfavorite(GalleryImage image);
}
