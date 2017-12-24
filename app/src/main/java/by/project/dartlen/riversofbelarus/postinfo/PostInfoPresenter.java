package by.project.dartlen.riversofbelarus.postinfo;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.Utils.Container;
import by.project.dartlen.riversofbelarus.data.LoadPostDataCallback;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.data.remote.Day;
import by.project.dartlen.riversofbelarus.data.remote.Note;
import by.project.dartlen.riversofbelarus.data.remote.Post;
import by.project.dartlen.riversofbelarus.posts.PostsContract;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 26.11.2017.
 */

public class PostInfoPresenter implements PostInfoContract.Presenter{

    @Nullable
    private PostInfoContract.View mPostInfoView;

    private final RiversRepository mRiversRepository;

    private String namePost;

    @Inject
    Router router;

    @Inject
    PostInfoPresenter(RiversRepository riversRepository){
        mRiversRepository=riversRepository;
    }

    @Override
    public void dropView() {
        mPostInfoView = null;
    }

    @Override
    public void takeView(PostInfoContract.View view) {
        mPostInfoView = view;
    }

    @Override
    public void onToolbarBackClicked(){
        router.exit();
    }

    @Override
    public void setPost(String namePost) {
        this.namePost = namePost;
    }

    @Override
    public void loadPosts() {
        mRiversRepository.getPostData(new LoadPostDataCallback() {
            @Override
            public void onPostDataLoaded(Post postData) {
                mPostInfoView.showDays(postData);
            }

            @Override
            public void onDataNotAvailable(String error) {

            }
        }, namePost);
    }

    @Override
    public void onClickedDay(Day day) {

        Container tmp = new Container(day, namePost);
        router.navigateTo("notesFragment", tmp);
    }
}
