package by.project.dartlen.riversofbelarus.di.module.main;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/***
 * Created by Dartlen on 21.11.2017.
 */

@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);
}
