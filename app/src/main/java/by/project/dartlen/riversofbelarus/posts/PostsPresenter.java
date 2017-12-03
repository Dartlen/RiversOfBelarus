package by.project.dartlen.riversofbelarus.posts;

import java.util.HashSet;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.LoadPostsCallback;
import by.project.dartlen.riversofbelarus.data.LoadRiversCallback;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 26.11.2017.
 */

@ActivityScope
public class PostsPresenter implements PostsContract.Presenter{

    @Nullable
    private PostsContract.View mPostsView;

    private final RiversRepository mRiversRepository;
    private String riverName="";

    @Inject
    Router router;

    @Inject
    PostsPresenter(RiversRepository riversRepository){
        mRiversRepository = riversRepository;
    }

    @Override
    public void dropView() {
        mPostsView = null;
    }

    @Override
    public void takeView(PostsContract.View view) {
        this.mPostsView = view;
    }

    @Override
    public void loadPosts() {
        mRiversRepository.getPosts(new LoadPostsCallback() {
            @Override
            public void onRiversLoaded(HashSet<String> postsList) {
                mPostsView.showPosts(postsList);
            }

            @Override
            public void onDataNotAvailable(String error) {

            }
        },riverName);
    }

    @Override
    public void onToolbarBackClicked() {
        router.exit();
    }

    public void setRiver(Object river){
        riverName=(String)river;
    }

    @Override
    public void onRecyclerViewClicked(String namePost) {
        router.navigateTo("postInfoFragment", namePost);
    }

}
