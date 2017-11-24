package by.project.dartlen.riversofbelarus.river;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;

import javax.inject.Inject;


import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import dagger.android.support.DaggerFragment;
import ru.terrakok.cicerone.Router;

import static com.google.common.base.Preconditions.checkNotNull;

/***
 * Created by Dartlen on 12.11.2017.
 */

@ActivityScope
public class RiverFragment extends DaggerFragment implements RiversContract.View{

    private HashSet<String> listData = new HashSet<>();

    @Inject
    RiversContract.Presenter mRiversPresenter;

    @Inject
    Router router;

    RecyclerView mRecyclerView;
    ListAdapter adapter;

    @Inject
    public RiverFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rivers, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ListAdapter(listData);

        mRecyclerView.setAdapter(adapter);

        mRiversPresenter.loadpost(listData);

        return root;
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
    public void showPosts(HashSet<String> list) {
        listData = list;
        adapter.addAll(list);

    }
}
