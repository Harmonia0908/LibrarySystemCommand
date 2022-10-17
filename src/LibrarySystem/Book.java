package LibrarySystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book extends CatalogItem {
    String author;
    int numberOfPages;

    public Book(String author, int numberOfPages) {
        super();
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Book(String code, String title, Date publishDate, String author, int numberOfPages) {
        super(code, title, publishDate);
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        String pattern = "yyyy-mm-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(publishDate);
        return "Book_" + code +
                "_" + title +
                "_" + dateString +
                "_" + author +
                "_" + numberOfPages;
    }
}
