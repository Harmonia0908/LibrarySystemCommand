package LibrarySystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recording extends CatalogItem{
    String performer;
    String format;

    public Recording(String performer, String format) {
        super();
        this.performer = performer;
        this.format = format;
    }

    public Recording(String code, String title, Date publishDate, String performer, String format) {
        super(code, title, publishDate);
        this.performer = performer;
        this.format = format;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        String pattern="yyyy-mm-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String dateString=sdf.format(publishDate);
        return "Recording_" + code +
                "_" + title +
                "_" + dateString +
                "_"+ performer +
                "_" +format;
    }
}
