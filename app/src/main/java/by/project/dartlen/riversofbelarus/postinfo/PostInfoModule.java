package by.project.dartlen.riversofbelarus.postinfo;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 26.11.2017.
 */
@Module
public abstract class PostInfoModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract PostInfoFragment postInfoFragment();

    @ActivityScope
    @Binds abstract PostInfoContract.Presenter postInfoPresenter(PostInfoPresenter presenter);
}
