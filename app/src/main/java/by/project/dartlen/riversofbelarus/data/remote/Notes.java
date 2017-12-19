package by.project.dartlen.riversofbelarus.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Dartlen on 18.12.2017.
 */

@IgnoreExtraProperties
public class Notes {
    public List<Note> days = new ArrayList<>();

    public List<Note> getDays() {
        return days;
    }

    public void setDays(List<Note> days) {
        this.days = days;
    }
}
