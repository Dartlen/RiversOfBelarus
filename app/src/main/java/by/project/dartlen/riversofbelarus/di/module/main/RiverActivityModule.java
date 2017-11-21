package by.project.dartlen.riversofbelarus.di.module.main;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import by.project.dartlen.riversofbelarus.river.RiversContract;
import by.project.dartlen.riversofbelarus.river.RiverFragment;
import by.project.dartlen.riversofbelarus.river.RiverPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/***
 * Created by Dartlen on 21.11.2017.
 */

@Module
public abstract class RiverActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract RiverFragment tasksFragment();

    @ActivityScope
    @Binds abstract RiversContract.Presenter taskPresenter(RiverPresenter presenter);

}
