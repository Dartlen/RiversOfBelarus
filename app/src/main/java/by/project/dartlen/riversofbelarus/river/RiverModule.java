package by.project.dartlen.riversofbelarus.river;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 21.11.2017.
 */
@Module
public abstract class RiverModule{
    
    @FragmentScope
    @ContributesAndroidInjector
    abstract RiverFragment riverFragment();
    
    @ActivityScope
    @Binds abstract RiversContract.Presenter riverPresenter(RiverPresenter presenter);
}
