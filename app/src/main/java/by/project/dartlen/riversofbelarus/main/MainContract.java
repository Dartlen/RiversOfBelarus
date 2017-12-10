package by.project.dartlen.riversofbelarus.main;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;

/***
 * Created by Dartlen on 09.12.2017.
 */

public interface MainContract {
    interface Presenter extends BasePresenter<MainContract.View>{
        void onClickedSignIn();
        void onClickedSignUp();
    }
    interface  View extends BaseView<MainContract.Presenter>{

    }
}
