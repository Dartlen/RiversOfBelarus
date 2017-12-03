package by.project.dartlen.riversofbelarus.rivers;

import java.util.HashSet;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.LoadPostsCallback;
import by.project.dartlen.riversofbelarus.data.LoadRiversCallback;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import ru.terrakok.cicerone.Router;

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
    Router router;

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
    public void loadRivers(final HashSet<String> list) {
        mRiversRepository.getRivers(new LoadRiversCallback() {
            @Override
            public void onRiversLoaded(HashSet<String> riversList) {
                mRiversView.showRivers(riversList);
            }

            @Override
            public void onDataNotAvailable(String error) {

            }
        });
    }
    
    @Override
    public void onToolbarExitClicked() {
        router.exit();
    }

    @Override
    public void onRecyclerViewClicked(String nameRiver) {
        router.navigateTo("postsFragment", nameRiver);
    }
}
