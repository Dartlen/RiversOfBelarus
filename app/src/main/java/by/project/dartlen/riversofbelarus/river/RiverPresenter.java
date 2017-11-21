package by.project.dartlen.riversofbelarus.river;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.LoadRiversCallback;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.data.remote.Post;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;

import static com.google.common.base.Preconditions.checkNotNull;

/***
 * Created by Dartlen on 12.11.2017.
 */

@ActivityScope
final public class RiverPresenter implements RiversContract.Presenter{

    @Nullable
    private RiversContract.View mRiversView;

    private final RiversRepository mRiversRepository;

    @Inject
    RiverPresenter(RiversRepository riversRepository){
        mRiversRepository = riversRepository;
    }

    @Override
    public void dropView() {
        mRiversView = null;
    }

    @Override
    public void takeView(RiversContract.View view) {
        this.mRiversView = view;
    }

    @Override
    public void loadpost(final List<Post> list) {
        mRiversRepository.getPosts(new LoadRiversCallback() {
            @Override
            public void onRiversLoaded(List<Post> riversList) {
                mRiversView.showPosts(riversList);
            }

            @Override
            public void onDataNotAvailable(String error) {

            }
        });
    }
}
