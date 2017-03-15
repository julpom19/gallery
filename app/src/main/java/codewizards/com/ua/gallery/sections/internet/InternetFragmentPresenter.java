package codewizards.com.ua.gallery.sections.internet;

import java.util.List;

import codewizards.com.ua.gallery.logic.net.google.GoogleSearchNetApi;
import codewizards.com.ua.gallery.model.net.Item;
import codewizards.com.ua.gallery.mvp.BasePresenter;

/**
 * Created by User on 27.02.2017.
 */

public class InternetFragmentPresenter extends BasePresenter {

    private InternetFragment view;
    private List<Item> items;

    public void setView(InternetFragment view) {
        this.view = view;
    }

    public void update() {
        if (items != null) {
            view.onImagesUpdated(items);
        }
    }

    public void search(String text) {
        view.startLoading();
        GoogleSearchNetApi.searchByQuery(text).subscribe(googleSearchResponse -> {
            this.items = googleSearchResponse.items;
            view.onImagesUpdated(items);
            view.finishLoading();
        });
    }

}
