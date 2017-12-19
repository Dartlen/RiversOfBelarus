package by.project.dartlen.riversofbelarus.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * Created by Dartlen on 18.12.2017.
 */
@IgnoreExtraProperties
public class Note {
    Long post;
    String text;
    Date to;

    public Long getPost() {
        return post;
    }

    public Date getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public void setTo(String to) {
        this.to = strDateToDate(to);
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date strDateToDate(String dateStr){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = dateFormat.parse(dateStr);
            return date;
        }catch(Exception e){
            return new Date();
        }
    }
}
