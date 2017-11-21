package by.project.dartlen.riversofbelarus.river;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.data.remote.Post;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import dagger.android.DaggerFragment;
import ru.terrakok.cicerone.Router;

import static com.google.common.base.Preconditions.checkNotNull;

/***
 * Created by Dartlen on 12.11.2017.
 */

@ActivityScope
public class RiverFragment extends DaggerFragment implements RiversContract.View{

    private List<Post> listData = new ArrayList<>();

    @Inject
    RiversContract.Presenter mRiversPresenter;

    @Inject
    Router router;

    @Inject
    public RiverFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<Post> x = new ArrayList<>();
        mRiversPresenter.loadpost(x);
        return inflater.inflate(R.layout.fragment_rivers, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        mRiversPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRiversPresenter.dropView();
    }

    @Override
    public void showPosts(List<Post> list) {
        listData = list;
    }
}
