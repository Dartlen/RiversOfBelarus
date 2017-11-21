package by.project.dartlen.riversofbelarus.di;

import android.app.Application;

import javax.inject.Singleton;

import by.project.dartlen.riversofbelarus.App;
import by.project.dartlen.riversofbelarus.data.RiversRepository;
import by.project.dartlen.riversofbelarus.data.RiversRepositoryModule;
import by.project.dartlen.riversofbelarus.di.module.main.ActivityBindingModule;
import by.project.dartlen.riversofbelarus.di.module.main.ApplicationModule;
import by.project.dartlen.riversofbelarus.di.module.main.NavigationModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/***
 * Created by Dartlen on 21.11.2017.
 */

@Singleton
@Component(modules = {RiversRepositoryModule.class,
        NavigationModule.class,
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(App application);

    RiversRepository getRiversRepository();

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
