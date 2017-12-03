package by.project.dartlen.riversofbelarus.postinfo;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.data.remote.Post;

/***
 * Created by Dartlen on 30.11.2017.
 */

public class PostInfoAdapter extends RecyclerView.Adapter<PostInfoViewHolder>{

    public Post data;

    public PostInfoAdapter(Post dataPost){
        this.data = dataPost;
    }

    @Override
    public PostInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_info, parent, false);
        PostInfoViewHolder vh = new PostInfoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PostInfoViewHolder holder, int position) {
        if(position%2==0) {
            holder.linearLayout.setBackgroundResource(R.drawable.postinforange);
        }else {
            holder.linearLayout.setBackgroundResource(R.drawable.postinfogreen);
        }

        holder.Temperature.setText(data.getDays().get(position).getTemperature().toString());
        holder.Level.setText(data.getDays().get(position).getLevel().toString());
        holder.Condition.setText(data.getDays().get(position).getIce().toString());
        changedLevel(position, holder);
    }

    @Override
    public int getItemCount() {
        return data.getDays().size();
    }

    public void setPost(Post data){
        this.data = data;
    }

    public void clearAll(){
        data=null;
    }

    public void changedLevel(int position, PostInfoViewHolder holder){
        String def;
        if(position+1<data.getDays().size()) {
            def = getDefference(data.getDays().get(position).getLevel(), data.getDays().get(position+1).getLevel());
            final SpannableStringBuilder text = new SpannableStringBuilder(data.getDays().get(position).getLevel().toString() + def);
            ForegroundColorSpan style;

            if ((data.getDays().get(position).getLevel() - data.getDays().get(position+1).getLevel()) > 0)
                style = new ForegroundColorSpan(Color.rgb(0, 255, 34));
            else
                style = new ForegroundColorSpan(Color.rgb(255, 0, 0));

            text.setSpan(style, text.length() - def.length(), text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            holder.Temperature.setText(text);
        }
    }

    private String getDefference(Integer valueOne, Integer valueTwo){
        if ((valueOne-valueTwo)>0)
            return "(+"+(valueOne-valueTwo)+")";
        else
            return "("+(valueOne-valueTwo)+")";
    }
}
