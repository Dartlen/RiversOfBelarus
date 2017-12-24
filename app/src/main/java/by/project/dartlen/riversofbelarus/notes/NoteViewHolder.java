package by.project.dartlen.riversofbelarus.notes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.project.dartlen.riversofbelarus.R;

/***
 * Created by Dartlen on 19.12.2017.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_text)
    TextView note_text;
    @BindView(R.id.note_date)
    TextView note_date;

    public NoteViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }
}
