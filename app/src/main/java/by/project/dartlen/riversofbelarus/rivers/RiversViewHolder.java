package by.project.dartlen.riversofbelarus.rivers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.project.dartlen.riversofbelarus.R;

/***
 * Created by Dartlen on 23.11.2017.
 */

public class RiversViewHolder extends RecyclerView.ViewHolder{
    public TextView mTextView;

    public RiversViewHolder(View v){
        super(v);
        mTextView=(TextView)v.findViewById(R.id.info_text);
    }
}