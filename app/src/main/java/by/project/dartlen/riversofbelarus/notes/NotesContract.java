package by.project.dartlen.riversofbelarus.notes;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;
import by.project.dartlen.riversofbelarus.data.remote.Day;

/***
 * Created by Dartlen on 17.12.2017.
 */

public interface NotesContract {
    interface Presenter extends BasePresenter<NotesContract.View>{
        void setNotes(Day day);
    }
    interface View extends BaseView<NotesContract.Presenter>{
        void setNotes(Object data);
    }
}
