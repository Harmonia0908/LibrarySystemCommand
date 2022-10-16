package LibrarySystem;

import java.util.Date;

public abstract class CatalogItem {
    String code;
    String title;
    Date publishDate;

    public CatalogItem() {
    }
    public CatalogItem(String code, String title, Date publishDate) {
        this.code = code;
        this.title = title;
        this.publishDate = publishDate;
    }


    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

}
