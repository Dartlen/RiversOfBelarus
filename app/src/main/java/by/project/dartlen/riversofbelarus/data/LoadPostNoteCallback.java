package by.project.dartlen.riversofbelarus.data;

import java.util.List;

/***
 * Created by Dartlen on 23.12.2017.
 */

public interface LoadPostNoteCallback {
    void onNoteDataLoaded(String noteData);

    void onDataNotAvailable(String error);
}
