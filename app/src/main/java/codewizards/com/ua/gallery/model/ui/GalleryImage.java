package codewizards.com.ua.gallery.model.ui;

/**
 * Created by Интернет on 19.01.2017.
 */

public class GalleryImage {
    private String url;
    private String name;
    private long date;
    private boolean isFavorite;

    public GalleryImage() {}

    public GalleryImage(String url, String name, long date) {
        this.url = url;
        this.name = name;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GalleryImage image = (GalleryImage) o;

        return url != null ? url.equals(image.url) : image.url == null;

    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GalleryImage{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
