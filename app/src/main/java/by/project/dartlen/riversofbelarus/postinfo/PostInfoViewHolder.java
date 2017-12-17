package by.project.dartlen.riversofbelarus.postinfo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.project.dartlen.riversofbelarus.R;

/***
 * Created by Dartlen on 30.11.2017.
 */

public class PostInfoViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.layout_day_info)
    LinearLayout linearLayout;
    @BindView(R.id.LevelValue)
    TextView Level;
    @BindView(R.id.TemperatureValue)
    TextView Temperature;
    @BindView(R.id.ConditionValue)
    TextView Condition;
    @BindView(R.id.Date)
    TextView Date;

    public PostInfoViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }


}
