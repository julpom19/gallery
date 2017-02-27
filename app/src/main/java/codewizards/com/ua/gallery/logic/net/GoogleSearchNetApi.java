package codewizards.com.ua.gallery.logic.net;

import codewizards.com.ua.gallery.model.net.GoogleSearchResponse;
import codewizards.com.ua.gallery.util.Const;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by madless on 30.07.2016.
 */
public class GoogleSearchNetApi {
    public static Observable<GoogleSearchResponse> searchByQuery(String query) {
        String apiKey = Const.GOOGLE_CUSTOM_SEARCH_API_KEY;
        String cx = Const.GOOGLE_CUSTOM_SEARCH_CX;
        String searchType = "image";
        String alt = "json";
        return GoogleSearchQueryManager.getInstance().getSearchApi().searchByQuery(apiKey, cx, query, alt, searchType)
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
