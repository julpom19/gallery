package codewizards.com.ua.gallery.mvp;

import android.support.annotation.NonNull;

/**
 * Created by madless on 30.07.2016.
 */
public interface PresenterFactory<T extends BasePresenter> {

    /**
     * Create a new instance of a Presenter
     *
     * @return The Presenter instance
     */
    @NonNull
    T createPresenter();
}