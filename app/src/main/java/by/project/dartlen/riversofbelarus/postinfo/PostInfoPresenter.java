package by.project.dartlen.riversofbelarus.postinfo;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.posts.PostsContract;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 26.11.2017.
 */

public class PostInfoPresenter implements PostInfoContract.Presenter{

    @Nullable
    private PostInfoContract.View mPostInfoView;

    private final RiversRepository mRiversRepository;

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
    public void backToPosts(){
        router.exit();
    }
}
