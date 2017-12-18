package by.project.dartlen.riversofbelarus.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.data.remote.Day;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import dagger.android.support.DaggerFragment;

/***
 * Created by Dartlen on 17.12.2017.
 */
@ActivityScope
public class NotesFragment extends DaggerFragment implements NotesContract.View{

    @Inject
    NotesContract.Presenter mNotesPresenter;

    @Inject
    public NotesFragment(){}

    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar)getActivity().findViewById(R.id.toolbar));
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mNotesPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotesPresenter.dropView();
    }

    @Override
    public void setNotes(Object data) {
        mNotesPresenter.setNotes((Day)data);
    }
}
