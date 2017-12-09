package by.project.dartlen.riversofbelarus.main;

import javax.annotation.Nullable;
import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import ru.terrakok.cicerone.Router;

/***
 * Created by Dartlen on 09.12.2017.
 */
@ActivityScope
public class MainPresenter implements MainContract.Presenter{
    @Nullable
    private MainContract.View mMainView;

    private final RiversRepository mRiverRepository;

    @Inject
    Router router;
    
    @Inject
    MainPresenter(RiversRepository riversRepository){
        mRiverRepository = riversRepository;
    }

    @Override
    public void dropView() {
        mMainView = null;
    }

    @Override
    public void takeView(MainContract.View view) {
        this.mMainView = view;
    }
}
