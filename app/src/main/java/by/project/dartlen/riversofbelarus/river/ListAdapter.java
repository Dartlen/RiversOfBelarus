package by.project.dartlen.riversofbelarus.river;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.data.remote.Post;

/***
 * Created by Dartlen on 23.11.2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ViewHolder>{
    private List<String> mDataset;
    // Конструктор
    public ListAdapter(HashSet<String> dataset) {
        mDataset = new ArrayList<String>(dataset);

    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position));

    }

    // Возвращает размер данных (вызывается layout manager-ом)
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


}