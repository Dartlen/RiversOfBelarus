package by.project.dartlen.riversofbelarus.di.module.main;

import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.river.RiverActivity;
import by.project.dartlen.riversofbelarus.river.RiverModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 21.11.2017.
 */

@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = RiverModule.class)
    abstract RiverActivity riverActivity();
}
