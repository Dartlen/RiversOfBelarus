package by.project.dartlen.riversofbelarus.postinfo;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;
import by.project.dartlen.riversofbelarus.data.remote.Post;

/***
 * Created by Dartlen on 26.11.2017.
 */

public interface PostInfoContract {
    interface Presenter extends BasePresenter<PostInfoContract.View>{
        void onToolbarBackClicked();
        void setPost(String namePost);
        void loadPosts();
    }

    interface View extends BaseView<PostInfoContract.Presenter>{
        void setPost(Object namePost);
        void showDays(Post dataPost);
    }
}
