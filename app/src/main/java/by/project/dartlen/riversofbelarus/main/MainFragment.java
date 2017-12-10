package by.project.dartlen.riversofbelarus.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.btnSignIn)
    Button btnSignIn;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.onClickedSignIn();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.onClickedSignUp();
            }
        });

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
