package codewizards.com.ua.gallery.model.db;

import io.realm.RealmObject;

/**
 * Created by User on 27.02.2017.
 */

public class RealmImage extends RealmObject {
    private String url;
    private String name;
    private long date;
    private boolean isFavorite;

    public RealmImage() {}

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RealmImage{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
