package by.project.dartlen.riversofbelarus.signin;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;

/***
 * Created by Dartlen on 09.12.2017.
 */

public interface SigninContract {
    interface Presenter extends BasePresenter<SigninContract.View>{
        void onClickedSignInButton(String phone, String password);
    }
    interface View extends BaseView<SigninContract.Presenter>{
        void showProgressDialog();
        void hideProgressDialog();
        void showFailedLogined();
    }
}
