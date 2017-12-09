package by.project.dartlen.riversofbelarus.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import dagger.android.support.DaggerFragment;

/***
 * Created by Dartlen on 09.12.2017.
 */
@ActivityScope
public class MainFragment extends DaggerFragment implements MainContract.View {

    @Inject
    MainContract.Presenter mMainPresenter;

    @Inject
    public MainFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
