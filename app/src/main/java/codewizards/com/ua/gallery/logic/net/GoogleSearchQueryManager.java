package codewizards.com.ua.gallery.logic.net;


import codewizards.com.ua.gallery.util.Const;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by madless on 31.07.2016.
 */
public class GoogleSearchQueryManager {
    private static GoogleSearchQueryManager instance;
    private Retrofit retrofit;
    private GoogleSearchServerApi searchApi;

    public static GoogleSearchQueryManager getInstance() {
        if(instance == null) {
            instance = new GoogleSearchQueryManager();
        }
        return instance;
    }

    private GoogleSearchQueryManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.GOOGLE_CUSTOM_SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        searchApi = retrofit.create(GoogleSearchServerApi.class);
    }

    public GoogleSearchServerApi getSearchApi() {
        return searchApi;
    }
}
