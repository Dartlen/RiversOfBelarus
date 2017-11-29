package by.project.dartlen.riversofbelarus.rivers;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
    RiversAdapter adapter;

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

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        mRecyclerView = root.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RiversAdapter(listData);

        mRecyclerView.setAdapter(adapter);

        mRiversPresenter.loadRivers(listData);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String riverName = new ArrayList<>(listData).get(position);
                router.navigateTo("postsFragment", riverName);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.exit));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar)getActivity().findViewById(R.id.toolbar));
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                mRiversPresenter.backToPosts();
            }
        });

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
    public void showRivers(HashSet<String> list) {
        listData = list;
        adapter.clearAll();
        adapter.notifyDataSetChanged();
        adapter.addAll(list);
    }
}
