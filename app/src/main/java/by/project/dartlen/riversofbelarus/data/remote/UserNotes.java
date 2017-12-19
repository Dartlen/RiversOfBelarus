package by.project.dartlen.riversofbelarus.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;

/***
 * Created by Dartlen on 18.12.2017.
 */
@IgnoreExtraProperties
public class UserNotes {

    Notes notes;

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}
