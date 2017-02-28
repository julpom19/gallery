package codewizards.com.ua.gallery.sections.internet;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import codewizards.com.ua.gallery.logic.net.google.GoogleSearchNetApi;
import codewizards.com.ua.gallery.logic.net.loader.PicLoader;
import codewizards.com.ua.gallery.model.net.Item;
import codewizards.com.ua.gallery.mvp.BasePresenter;
import codewizards.com.ua.gallery.util.FileHelper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by User on 27.02.2017.
 */

public class InternetFragmentPresenter extends BasePresenter {

    private InternetFragment view;
    private List<Item> items;
    String fileName = null;

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

    public void loadImage(Item item) {
        view.startLoading();
        logger.d("Loading item: " + item);
        String regex = "(?=[\\w|-]+\\.\\w{3,4}$).+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(item.link);
        if (matcher.find()) {
            fileName = matcher.group();
        }
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                PicLoader picLoader = new PicLoader();
                picLoader.load(item.link, new PicLoader.ProgressListener() {
                    @Override
                    public void onUpdate(long bytesRead, long contentLength, boolean done) {
                        long percents = (100 * bytesRead) / contentLength;
                        subscriber.onNext((int) percents);
                    }

                    @Override
                    public void onLoaded(InputStream stream) {
                        FileHelper.saveFile(stream, fileName);
                        logger.d("File saved!");
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        })
                .buffer(100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(List<Integer> integers) {
                        return Observable.just(integers.get(integers.size() - 1));
                    }
                })
                .subscribe(percents -> {
                    logger.d(String.format("%d%%\n", percents));
                    view.onImageLoadingUpdated(percents);
                },
                Throwable::printStackTrace,
                () -> {
                    view.finishLoading();
                    view.onImageLoadingFinished();
                });
    }
}
