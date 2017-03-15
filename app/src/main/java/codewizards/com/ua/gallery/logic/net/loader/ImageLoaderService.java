package codewizards.com.ua.gallery.logic.net.loader;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationManagerCompat;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.util.Const;
import codewizards.com.ua.gallery.util.FileHelper;
import codewizards.com.ua.gallery.util.Logger;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dmikhov on 3/15/2017.
 */

public class ImageLoaderService extends IntentService {

    private static final Integer LOADING_NOTIFICATION_ID = 101;

    private Logger logger = Logger.getLogger(this.getClass());
    private String photoName;

    public ImageLoaderService() {
        super("ImageLoaderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String photoUrl = intent.getStringExtra(Const.EXTRA_PHOTO_URL);
        loadImage(photoUrl);
    }

    public void loadImage(String photoUrl) {
        logger.d("Loading item: " + photoUrl);
        photoName = photoUrl;
        String regex = "(?=[\\w|-]+\\.\\w{3,4}$).+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(photoUrl);
        if (matcher.find()) {
            photoName = matcher.group();
        }
        onImageLoadingStarted();
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                PicLoader picLoader = new PicLoader();
                picLoader.load(photoUrl, new PicLoader.ProgressListener() {
                    @Override
                    public void onUpdate(long bytesRead, long contentLength, boolean done) {
                        long percents = (100 * bytesRead) / contentLength;
                        subscriber.onNext((int) percents);
                    }

                    @Override
                    public void onLoaded(InputStream stream) {
                        File dirDownloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        File file = new File(dirDownloads, photoName);
                        FileHelper.saveFile(stream, file);
                        FileHelper.refreshContentProvider(file.getAbsolutePath());
                        logger.d("File saved!");
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });}})
                .buffer(100)
                .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(List<Integer> integers) {
                        return Observable.just(integers.get(integers.size() - 1));
                    }
                })
                .subscribe(this::onImageLoadingUpdated,
                        Throwable::printStackTrace,
                        this::onImageLoadingFinished);
    }

    public void onImageLoadingStarted() {
        startForeground(LOADING_NOTIFICATION_ID, getImageLoadingNotificationBuilder().build());
    }

    public Notification.Builder getImageLoadingNotificationBuilder() {
        return new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.service_loading_title))
                .setSmallIcon(R.drawable.ic_load);
    }

    public void onImageLoadingUpdated(int percents) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        String text = String.format(Locale.ENGLISH, getString(R.string.service_loading_in_progress), percents);
        Notification notification = getImageLoadingNotificationBuilder().setContentText(text).build();
        manager.notify(LOADING_NOTIFICATION_ID, notification);
    }

    public void onImageLoadingFinished() {
        stopForeground(true);
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.service_loading_finished))
                .setContentText(getString(R.string.service_loading_finished_successfully))
                .setSmallIcon(R.drawable.ic_load)
                .build();
        manager.notify(LOADING_NOTIFICATION_ID, notification);
    }
}
