package by.project.dartlen.riversofbelarus.signin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
public class SigninFragment extends DaggerFragment implements SigninContract.View{

    @Inject
    SigninContract.Presenter mSigninPresenter;

    @Inject
    public SigninFragment(){}

    @BindView(R.id.btnSignIn)
    Button btnSignIn;

    @BindView(R.id.editPassword)
    EditText editTextPassword;

    @BindView(R.id.editPhone)
    EditText editTextPhone;

    private ProgressDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = new ProgressDialog(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, root);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSigninPresenter.onClickedSignInButton(editTextPhone.getText().toString(),
                        editTextPassword.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void showProgressDialog() {

        mDialog.setMessage("Пожалуйста подождите");
        mDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mDialog.dismiss();
    }

    @Override
    public void showFailedLogined() {
        Toast.makeText(getActivity(), "Ошибка входа, неверный номер или пароль!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSigninPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
