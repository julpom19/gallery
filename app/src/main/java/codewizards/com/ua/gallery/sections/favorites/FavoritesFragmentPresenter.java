package codewizards.com.ua.gallery.sections.favorites;

import java.util.List;

import codewizards.com.ua.gallery.logic.db.DbEventObserver;
import codewizards.com.ua.gallery.logic.db.DbManager;
import codewizards.com.ua.gallery.model.ui.GalleryImage;
import codewizards.com.ua.gallery.mvp.BasePresenter;

/**
 * Created by User on 27.02.2017.
 */

public class FavoritesFragmentPresenter extends BasePresenter implements DbEventObserver {

    private FavoritesFragment view;
    private List<GalleryImage> images;
    private DbManager repository = DbManager.get();

    public void setView(FavoritesFragment view) {
        this.view = view;
    }

    public void update() {
        if(images != null) {
            view.updateImages(images);
        } else {
            repository.addObserver(this);
            fetchFreshData();
        }
    }

    public void fetchFreshData() {
        images = repository.getFavoriteImages();
        view.updateImages(images);
    }

    public void addToFavorite(GalleryImage image) {
        repository.addImageToFavorite(this, image);
    }

    public void removeFromFavorites(GalleryImage image) {
        repository.removeImageFromFavorites(this, image);
        images.remove(image);
        view.updateImages(images);
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
