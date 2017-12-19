package by.project.dartlen.riversofbelarus.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.data.remote.Note;

/***
 * Created by Dartlen on 19.12.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder>{

    public List<Note> notes = new ArrayList<>(0);

    public NotesAdapter(List<Note> dataNotes){ this.notes = dataNotes;}

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.note_text.setText(notes.get(position).getText());
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        NoteViewHolder vh = new NoteViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        if(notes==null)
            return 0;
        else
            return notes.size();
    }

    public void clearAll(){ notes = null;}

    public void setNotes(List<Note> dataNotes){ this.notes = dataNotes;}
}
