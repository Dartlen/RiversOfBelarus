package by.project.dartlen.riversofbelarus.signin;

import android.widget.Toast;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.LoadUserCallback;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.data.remote.User;
import by.project.dartlen.riversofbelarus.rivers.RiverActivity;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 09.12.2017.
 */

public class SigninPresenter implements SigninContract.Presenter{

    @Inject
    Router router;

    @Nullable
    private SigninContract.View mSigninView;

    private final RiversRepository mRiverRepository;

    @Inject
    SigninPresenter(RiversRepository riversRepository){
        mRiverRepository = riversRepository;
    }

    @Override
    public void takeView(SigninContract.View view) {
        this.mSigninView = view;
    }

    @Override
    public void dropView() {
        this.mSigninView = null;
    }

    @Override
    public void onClickedSignInButton(String phone, final String password) {

        mSigninView.showProgressDialog();

        mRiverRepository.getUser(new LoadUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                if(user.getPassword().equals(password)){
                    mSigninView.hideProgressDialog();
                    router.navigateTo("riverFragment");
                }else{
                    mSigninView.hideProgressDialog();
                    mSigninView.showFailedLogined();
                }

            }

            @Override
            public void onDataNotAvailable(String error) {
                mSigninView.hideProgressDialog();
                mSigninView.showFailedLogined();
            }
        }, phone);
    }
}
