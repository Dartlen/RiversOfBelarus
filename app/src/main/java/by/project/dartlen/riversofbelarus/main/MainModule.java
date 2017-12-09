package by.project.dartlen.riversofbelarus.main;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 09.12.2017.
 */
@Module
public abstract class MainModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ActivityScope
    @Binds abstract MainContract.Presenter mainPresenter(MainPresenter presenter);

}
