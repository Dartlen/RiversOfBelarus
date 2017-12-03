package by.project.dartlen.riversofbelarus.rivers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import by.project.dartlen.riversofbelarus.R;

/***
 * Created by Dartlen on 23.11.2017.
 */

public class RiversAdapter extends RecyclerView.Adapter<RiversViewHolder>{
    private List<String> mDataset;
    private boolean flag=true;

    public RiversAdapter(HashSet<String> dataset) {
        mDataset = new ArrayList<>(dataset);
    }


    @Override
    public RiversViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        RiversViewHolder vh = new RiversViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RiversViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position));

    }
    @Override
    public int getItemCount() {
        return mDataset.size();

    }

    public void addAll(HashSet<String> list){
        for (String result : list) {
            add(result);
        }
    }

    public void add(String r) {
        mDataset.add(r);
        notifyItemInserted(mDataset.size() - 1);
    }

    public void clearAll(){
        mDataset.clear();
    }
}