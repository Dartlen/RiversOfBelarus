package by.project.dartlen.riversofbelarus.posts;

import java.util.HashSet;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;

/***
 * Created by Dartlen on 26.11.2017.
 */

public interface PostsContract {
    interface Presenter extends BasePresenter<PostsContract.View> {
        void loadPosts();
        void onToolbarBackClicked();
        void setRiver(Object river);
        void onRecyclerViewClicked(String namePost);
    }

    interface View extends BaseView<PostsContract.Presenter> {
        void showPosts(HashSet<String> list);
        void setRiver(Object river);
    }
}
