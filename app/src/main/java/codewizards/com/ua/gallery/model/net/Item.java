
package codewizards.com.ua.gallery.model.net;


public class Item {

    private String kind;
    private String title;
    private String htmlTitle;
    private String link;
    private String displayLink;
    private String snippet;
    private String htmlSnippet;
    private String mime;
    private String fileFormat;
    private Image image;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Item{" +
                "kind='" + kind + '\'' +
                ", title='" + title + '\'' +
                ", htmlTitle='" + htmlTitle + '\'' +
                ", link='" + link + '\'' +
                ", displayLink='" + displayLink + '\'' +
                ", snippet='" + snippet + '\'' +
                ", htmlSnippet='" + htmlSnippet + '\'' +
                ", mime='" + mime + '\'' +
                ", fileFormat='" + fileFormat + '\'' +
                ", image=" + image +
                '}';
    }
}
