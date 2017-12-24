package by.project.dartlen.riversofbelarus.notes;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.Utils.Container;
import by.project.dartlen.riversofbelarus.data.LoadNotesCallback;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.data.remote.Day;
import by.project.dartlen.riversofbelarus.data.LoadPostNoteCallback;
import by.project.dartlen.riversofbelarus.data.remote.Note;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 17.12.2017.
 */

public class NotesPresenter implements NotesContract.Presenter{

    @NotNull
    private NotesContract.View mNotesView;

    private final RiversRepository mRiverRepository;

    private Day Day;
    private String namePost;

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
    public void setNotes(final Object data) {
        Container tmp = (Container)data;
        Day = tmp.getDay();
        namePost = tmp.getNamePost();
    }

    @Override
    public void loadNotes() {
        mRiverRepository.loadNotes(new LoadNotesCallback() {
            @Override
            public void onNoteDataLoaded(List<Note> notesData) {
                mNotesView.showNotes(notesData);

            }

            @Override
            public void onDataNotAvailable(String error) {

            }
        }, Day, namePost);
    }

    @Override
    public void onToolbarBackClicked() {
        router.exit();
    }

    @Override
    public void fabClicked(String note) {
        mRiverRepository.postNote(new LoadPostNoteCallback() {
            @Override
            public void onNoteDataLoaded(String noteData) {

            }

            @Override
            public void onDataNotAvailable(String error) {
                mNotesView.showError(error);
            }
        }, note, Day.getTo(), namePost);
    }
}


