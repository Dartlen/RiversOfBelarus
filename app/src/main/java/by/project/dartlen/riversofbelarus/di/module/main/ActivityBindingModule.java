package by.project.dartlen.riversofbelarus.di.module.main;

import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.main.MainModule;
import by.project.dartlen.riversofbelarus.notes.NotesModule;
import by.project.dartlen.riversofbelarus.postinfo.PostInfoModule;
import by.project.dartlen.riversofbelarus.posts.PostsModule;
import by.project.dartlen.riversofbelarus.rivers.RiverActivity;
import by.project.dartlen.riversofbelarus.rivers.RiverModule;
import by.project.dartlen.riversofbelarus.signin.SigninModule;
import by.project.dartlen.riversofbelarus.signup.SignupModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 21.11.2017.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {RiverModule.class, MainModule.class, SigninModule.class,
            SignupModule.class, PostsModule.class, PostInfoModule.class, NotesModule.class})
    abstract RiverActivity riverActivity();
}
