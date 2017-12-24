package by.project.dartlen.riversofbelarus.notes;

import java.util.List;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;
import by.project.dartlen.riversofbelarus.data.remote.Day;
import by.project.dartlen.riversofbelarus.data.remote.Note;

/***
 * Created by Dartlen on 17.12.2017.
 */

public interface NotesContract {
    interface Presenter extends BasePresenter<NotesContract.View>{
        void setNotes(Object day);
        void onToolbarBackClicked();
        void loadNotes();
        void fabClicked(String text);
    }
    interface View extends BaseView<NotesContract.Presenter>{
        void setNotes(Object data);
        void showNotes(List<Note> notesData);
        void showError(String Value);
    }
}
