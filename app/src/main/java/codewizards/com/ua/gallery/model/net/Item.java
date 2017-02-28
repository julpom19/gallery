
package codewizards.com.ua.gallery.model.net;


public class Item {

    public String kind;
    public String title;
    public String htmlTitle;
    public String link;
    public String displayLink;
    public String snippet;
    public String htmlSnippet;
    public String mime;
    public String fileFormat;
    public Image image;

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
