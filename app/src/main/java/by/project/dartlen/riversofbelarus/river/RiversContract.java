package by.project.dartlen.riversofbelarus.river;

import java.util.List;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;
import by.project.dartlen.riversofbelarus.data.remote.Post;

/**
 * Created by Dartlen on 12.11.2017.
 */

public interface RiversContract {
    interface Presenter extends BasePresenter<View>{
        void loadpost(List<Post> list);
    }

    interface View extends BaseView<Presenter>{
        void showPosts(List<Post> list);
    }
}
