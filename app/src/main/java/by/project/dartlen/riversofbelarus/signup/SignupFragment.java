package by.project.dartlen.riversofbelarus.signup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class SignupFragment extends DaggerFragment implements SignupContract.View{
    @Inject
    SignupContract.Presenter mSignupPresenter;

    @Inject
    public SignupFragment(){}

    @BindView(R.id.editPhone)
    EditText editTextPhone;

    @BindView(R.id.editName)
    EditText editTextName;

    @BindView(R.id.editPassword)
    EditText editTextPassword;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    private ProgressDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = new ProgressDialog(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        ButterKnife.bind(this, root);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignupPresenter.onClickedSignUp(editTextPhone.getText().toString(),
                                                 editTextName.getText().toString(),
                                                 editTextPassword.getText().toString());
            }
        });


        return root;
    }

    @Override
    public void showFailedRegister() {
        Toast.makeText(getActivity(), "Ошибка регистрации, такой пользователь уже зарегестрирован!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        mDialog.setMessage("Пожалуйста подождите");
        mDialog.show();
    }

    @Override
    public void showUserRegisterMessage() {
        Toast.makeText(getActivity(), "Пользователь устешно зарегестрирован!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgressDialog() {
        mDialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSignupPresenter.takeView(this);
    }
}
