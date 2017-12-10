package by.project.dartlen.riversofbelarus.signup;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;

/***
 * Created by Dartlen on 09.12.2017.
 */

public interface SignupContract {
    interface Presenter extends BasePresenter<SignupContract.View>{
        void onClickedSignUp(String phone, String Name, String password);
    }
    interface View extends BaseView<SignupContract.Presenter>{
        void showProgressDialog();
        void hideProgressDialog();
        void showFailedRegister();
        void showUserRegisterMessage();
    }
}
