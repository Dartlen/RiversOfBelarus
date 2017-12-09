package by.project.dartlen.riversofbelarus.di.module.main;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.main.MainModule;
import by.project.dartlen.riversofbelarus.postinfo.PostInfoFragment;
import by.project.dartlen.riversofbelarus.postinfo.PostInfoModule;
import by.project.dartlen.riversofbelarus.posts.PostsModule;
import by.project.dartlen.riversofbelarus.rivers.RiverActivity;
import by.project.dartlen.riversofbelarus.rivers.RiverModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 21.11.2017.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {RiverModule.class, MainModule.class, PostsModule.class, PostInfoModule.class})
    abstract RiverActivity riverActivity();
}
