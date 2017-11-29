package by.project.dartlen.riversofbelarus.postinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.R;
import dagger.android.support.DaggerFragment;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 26.11.2017.
 */

public class PostInfoFragment extends DaggerFragment implements PostInfoContract.View {

    @Inject
    PostInfoContract.Presenter mPostInfoPresenter;

    @Inject
    Router router;

    @Inject
    public PostInfoFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_postinfo, container, false);
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
                    collapsingToolbarLayout.setTitle("Сегодня: 450(+0);Т:18°C");


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
               ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar)getActivity().findViewById(R.id.toolbar));
               ((AppCompatActivity) getActivity()).getSupportActionBar().show();
               mPostInfoPresenter.backToPosts();
            }
        });

        return root;
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
}
