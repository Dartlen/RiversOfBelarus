package by.project.dartlen.riversofbelarus;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.data.RiversRepository;

import by.project.dartlen.riversofbelarus.di.AppComponent;
import by.project.dartlen.riversofbelarus.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ru.terrakok.cicerone.NavigatorHolder;

/***
 * Created by Dartlen on 21.11.2017.
 */

public class App extends DaggerApplication {

    @Inject
    RiversRepository riversRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector(){
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    @VisibleForTesting
    public RiversRepository getTasksRepository() {
        return riversRepository;
    }

}
