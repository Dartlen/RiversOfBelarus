package by.project.dartlen.riversofbelarus.postinfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;

import by.project.dartlen.riversofbelarus.R;

/***
 * Created by Dartlen on 30.11.2017.
 */

public class PostInfoAdapter extends RecyclerView.Adapter<PostInfoViewHolder>{

    @Override
    public PostInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_info, parent, false);
        PostInfoViewHolder vh = new PostInfoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PostInfoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
