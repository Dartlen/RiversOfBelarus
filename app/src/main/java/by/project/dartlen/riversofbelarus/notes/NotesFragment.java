package by.project.dartlen.riversofbelarus.notes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.data.remote.Day;
import by.project.dartlen.riversofbelarus.data.remote.Note;
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

    @BindView(R.id.appendNote)
    FloatingActionButton addNote;

    @BindView(R.id.recycler_view_notes)
    RecyclerView recycler_view_notes;


    private Toolbar toolbar;
    private List<Note> notes;
    private NotesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        ButterKnife.bind(this, root);
        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar)getActivity().findViewById(R.id.toolbar));
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotesPresenter.onToolbarBackClicked();
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity());

                final View mView = layoutInflaterAndroid.inflate(R.layout.input_dialog, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.InputDialog);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                mNotesPresenter.fabClicked(userInputDialogEditText.getText().toString());
                            }
                        })

                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();

            }});

        recycler_view_notes.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycler_view_notes.setLayoutManager(mLayoutManager);
        adapter = new NotesAdapter(notes);

        RecyclerView.ItemDecoration mItemDecoration = new DividerItemDecoration(recycler_view_notes.getContext(), 1);
        recycler_view_notes.addItemDecoration(mItemDecoration);
        recycler_view_notes.setAdapter(adapter);

        mNotesPresenter.loadNotes();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        notes = null;
        mNotesPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        notes = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotesPresenter.dropView();
    }

    @Override
    public void setNotes(Object data) {
        mNotesPresenter.setNotes(data);
    }

    @Override
    public void showNotes(List<Note> notesData) {
        this.notes = notesData;

        adapter.clearAll();
        adapter.notifyDataSetChanged();
        adapter.setNotes(notesData);
    }

    @Override
    public void showError(String messtageError) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog alert = builder.create();

        builder.setMessage(messtageError).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.cancel();
            }
        });

    }
}
