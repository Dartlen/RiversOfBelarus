package by.project.dartlen.riversofbelarus.signup;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 09.12.2017.
 */
@Module
public abstract class SignupModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract SignupFragment signupFragment();

    @ActivityScope
    @Binds abstract SignupContract.Presenter signupPresenter(SignupPresenter presenter);

}
