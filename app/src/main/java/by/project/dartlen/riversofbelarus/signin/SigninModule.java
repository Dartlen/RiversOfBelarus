package by.project.dartlen.riversofbelarus.signin;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 09.12.2017.
 */
@Module
public abstract class SigninModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract SigninFragment signinFragment();

    @ActivityScope
    @Binds abstract SigninContract.Presenter signPresenter(SigninPresenter presenter);
}
