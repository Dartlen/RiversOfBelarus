package by.project.dartlen.riversofbelarus.posts;

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
import by.project.dartlen.riversofbelarus.rivers.RiversAdapter;
import by.project.dartlen.riversofbelarus.rivers.RecyclerItemClickListener;
import dagger.android.support.DaggerFragment;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 26.11.2017.
 */

@ActivityScope
public class PostsFragment extends DaggerFragment implements PostsContract.View {

    private HashSet<String> listData = new HashSet<>();

    @Inject
    PostsContract.Presenter mPostsPresenter;

    @Inject
    Router router;

    RecyclerView mRecyclerView;
    RiversAdapter adapter;

    @Inject
    public PostsFragment(){}

    @Override
    public void showPosts(HashSet<String> list) {
        listData = list;
        adapter.clearAll();
        adapter.notifyDataSetChanged();
        adapter.addAll(list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_posts, container, false);

        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_arrow));

        mRecyclerView = root.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RiversAdapter(listData);

        mRecyclerView.setAdapter(adapter);

        mPostsPresenter.loadPosts(listData);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String postName = new ArrayList<>(listData).get(position);
                        //router.replaceScreen("postInfoFragment", postName);
                        router.navigateTo("postInfoFragment", postName);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar)getActivity().findViewById(R.id.toolbar));
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                mPostsPresenter.backToPosts();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPostsPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPostsPresenter.dropView();
    }

    public void setRiver(Object nameRiver){
        mPostsPresenter.setRiver(nameRiver);
    }
}
