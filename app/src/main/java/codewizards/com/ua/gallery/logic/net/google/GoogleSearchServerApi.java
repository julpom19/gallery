package codewizards.com.ua.gallery.logic.net.google;


import codewizards.com.ua.gallery.model.net.GoogleSearchResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by User on 19.01.2016.
 */
public interface GoogleSearchServerApi {
    // https://www.googleapis.com/customsearch/v1?key=AIzaSyBC71LSergMeFo-puQtPYx5UoJE1KQjwvw&cx=000686061627594488699:ms8lhbqmkwk&q=picasso&alt=json
    @GET("/customsearch/v1")
    public Observable<GoogleSearchResponse> searchByQuery(@Query("key") String apiKey, @Query("cx") String cx,
                                                          @Query("q") String query, @Query("alt") String alt,
                                                          @Query("searchType") String searchType);
}
