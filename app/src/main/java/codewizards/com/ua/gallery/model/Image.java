package codewizards.com.ua.gallery.model;

/**
 * Created by Интернет on 19.01.2017.
 */

public class Image {
    private String url;
    private String name;
    private long date;

    public Image(String url, String name, long date) {
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

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
