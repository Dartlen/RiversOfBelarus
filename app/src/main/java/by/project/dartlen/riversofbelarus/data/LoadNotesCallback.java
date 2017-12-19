package by.project.dartlen.riversofbelarus.data;

import java.util.List;

import by.project.dartlen.riversofbelarus.data.remote.Note;
import by.project.dartlen.riversofbelarus.data.remote.Notes;
import by.project.dartlen.riversofbelarus.data.remote.Post;

/***
 * Created by Dartlen on 18.12.2017.
 */

public interface LoadNotesCallback {
    void onNoteDataLoaded(List<Note> notesData);

    void onDataNotAvailable(String error);
}
