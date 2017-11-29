package by.project.dartlen.riversofbelarus.posts;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 26.11.2017.
 */
@Module
public abstract class PostsModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract PostsFragment postsFragment();

    @ActivityScope
    @Binds abstract PostsContract.Presenter postsPresenter(PostsPresenter presenter);
}
