package by.project.dartlen.riversofbelarus.signup;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.CheckUserCallback;
import by.project.dartlen.riversofbelarus.data.LoadUserCallback;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.data.remote.User;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 09.12.2017.
 */

public class SignupPresenter implements SignupContract.Presenter{

    @Inject
    Router router;

    @Nullable
    private SignupContract.View mSignupView;

    private final RiversRepository mRiverRepository;

    @Inject
    SignupPresenter(RiversRepository riversRepository){
        this.mRiverRepository = riversRepository;
    }

    @Override
    public void takeView(SignupContract.View view) {
        this.mSignupView = view;
    }

    @Override
    public void dropView() {
        mSignupView = null;
    }

    @Override
    public void onClickedSignUp(String phone, final String name, String password) {
        mSignupView.showProgressDialog();

        mRiverRepository.registerUser(new LoadUserCallback() {
          @Override
          public void onUserLoaded(User user) {
            mSignupView.hideProgressDialog();
            router.navigateTo("riverFragment");
            mSignupView.showUserRegisterMessage();
          }

          @Override
          public void onDataNotAvailable(String error) {
              mSignupView.hideProgressDialog();
              mSignupView.showFailedRegister();

          }
      }, phone, name, password);

    }

}
