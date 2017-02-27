package codewizards.com.ua.gallery.util.converters;

import java.util.ArrayList;
import java.util.List;

import codewizards.com.ua.gallery.model.db.RealmImage;
import codewizards.com.ua.gallery.model.ui.GalleryImage;
import io.realm.RealmList;

/**
 * Created by User on 27.02.2017.
 */

public class RealmConverterImage implements IConverter<GalleryImage, RealmImage> {
    @Override
    public GalleryImage convertToUi(RealmImage item) {
        GalleryImage image = new GalleryImage();
        image.setDate(item.getDate());
        image.setName(item.getName());
        image.setUrl(item.getUrl());
        image.setFavorite(item.isFavorite());
        return image;
    }

    @Override
    public List<GalleryImage> convertToUi(List<RealmImage> items) {
        List<GalleryImage> images = new ArrayList<>();
        for (RealmImage realmImage: items) {
            images.add(convertToUi(realmImage));
        }
        return images;
    }

    @Override
    public RealmImage convertFromUi(GalleryImage item) {
        RealmImage realmImage = new RealmImage();
        realmImage.setDate(item.getDate());
        realmImage.setName(item.getName());
        realmImage.setUrl(item.getUrl());
        realmImage.setFavorite(item.isFavorite());
        return realmImage;
    }

    @Override
    public List<RealmImage> convertFromUi(List<GalleryImage> items) {
        RealmList<RealmImage> realmImages = new RealmList<>();
        for (GalleryImage image: items) {
            realmImages.add(convertFromUi(image));
        }
        return realmImages;
    }
}
