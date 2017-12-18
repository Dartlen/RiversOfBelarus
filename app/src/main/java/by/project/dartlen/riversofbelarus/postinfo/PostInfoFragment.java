package by.project.dartlen.riversofbelarus.postinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.w3c.dom.Text;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.data.remote.Day;
import by.project.dartlen.riversofbelarus.data.remote.Post;
import dagger.android.support.DaggerFragment;


/***
 * Created by Dartlen on 26.11.2017.
 */

public class PostInfoFragment extends DaggerFragment implements PostInfoContract.View,
        DatePickerDialog.OnDateSetListener {

    public PostInfoAdapter adapter;
    public Post dataPost = new Post();

    @Inject
    PostInfoContract.Presenter mPostInfoPresenter;

    @Inject
    public PostInfoFragment(){}

    @BindView(R.id.recycler_view_post_info)
    RecyclerView mRecyclerView;

    @BindView(R.id.calendar)
    ImageView calendar;

    @BindView(R.id.levelToolbar)
    TextView levelToolbar;

    @BindView(R.id.temperatureToolbar)
    TextView temperatureToolbar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_postinfo, container, false);
        ButterKnife.bind(this, root);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_arrow));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).supportPostponeEnterTransition();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).supportStartPostponedEnterTransition();

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) root.findViewById(R.id.collapsing_toolbar);

        AppBarLayout appBarLayout = (AppBarLayout) root.findViewById(R.id.app_bar_layout);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {

                    collapsingToolbarLayout.setTitle("Сегодня: "+dataPost.getDays().get(0).getLevel()+";Т:"+
                            dataPost.getDays().get(0).getTemperature()+"°C");//"Сегодня: 450(+0);Т:18°C");

                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");

                    isShow = false;
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mPostInfoPresenter.onToolbarBackClicked();
            }
        });

        Calendar now = Calendar.getInstance();
        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show(getFragmentManager(),"calendar");
            }
        });

        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        mPostInfoPresenter.onClickedDay(dataPost.getDays().get(position));
                    }
                });

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new PostInfoAdapter(dataPost);

        RecyclerView.ItemDecoration mItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), 1);
        mRecyclerView.addItemDecoration(mItemDecoration);
        mRecyclerView.setAdapter(adapter);
        mPostInfoPresenter.loadPosts();

        return root;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        //TODO: изменить отображаемые элементы в рецейкле

        for(Day x : dataPost.getDays())
            x.getTo();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPostInfoPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPostInfoPresenter.dropView();
    }

    @Override
    public void setPost(Object namePost) {
        mPostInfoPresenter.setPost((String)namePost);
    }

    @Override
    public void showDays(Post dataPost) {
        this.dataPost = dataPost;
        temperatureToolbar.setText(dataPost.getDays().get(0).getTemperature()+"°C");
        levelToolbar.setText(dataPost.getDays().get(0).getLevel()+"");
        adapter.clearAll();
        adapter.notifyDataSetChanged();
        adapter.setPost(dataPost);
    }
}
