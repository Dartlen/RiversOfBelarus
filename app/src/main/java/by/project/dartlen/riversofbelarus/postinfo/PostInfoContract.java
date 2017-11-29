package by.project.dartlen.riversofbelarus.postinfo;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;

/***
 * Created by Dartlen on 26.11.2017.
 */

public interface PostInfoContract {
    interface Presenter extends BasePresenter<PostInfoContract.View>{
        void backToPosts();
    }

    interface View extends BaseView<PostInfoContract.Presenter>{

    }
}
