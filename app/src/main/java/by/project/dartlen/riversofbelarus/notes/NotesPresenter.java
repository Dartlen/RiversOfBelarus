package by.project.dartlen.riversofbelarus.notes;

import org.greenrobot.greendao.annotation.NotNull;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.data.remote.Day;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 17.12.2017.
 */

public class NotesPresenter implements NotesContract.Presenter{

    @NotNull
    private NotesContract.View mNotesView;

    private final RiversRepository mRiverRepository;

    @Inject
    Router router;

    @Inject
    NotesPresenter(RiversRepository riversRepository){
        mRiverRepository = riversRepository;
    }

    @Override
    public void takeView(NotesContract.View view) {
        mNotesView = view;
    }

    @Override
    public void dropView() {
        mNotesView = null;
    }

    @Override
    public void setNotes(Day day) {
        //TODO: loading notes if they exits
    }
}
