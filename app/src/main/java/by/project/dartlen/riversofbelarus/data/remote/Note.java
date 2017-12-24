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
    String post;
    String text;
    Date to;
    String key = "";

    public String getPost() {
        return post;
    }

    public String getTo() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(to);
    }

    public String getText() {
        return text;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setTo(String to) {
        this.to = strDateToDate(to);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
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
