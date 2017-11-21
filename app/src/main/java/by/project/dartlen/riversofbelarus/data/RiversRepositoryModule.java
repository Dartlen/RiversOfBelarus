package by.project.dartlen.riversofbelarus.data;

import com.google.j2objc.annotations.RetainedLocalRef;

import javax.inject.Singleton;

import by.project.dartlen.riversofbelarus.data.remote.RiversRemoteData;
import dagger.Binds;
import dagger.Module;

/***
 * Created by Dartlen on 21.11.2017.
 */
@Module
public abstract class  RiversRepositoryModule {

    /*@Singleton
    @Binds
    @Local
    abstract RiversLocalData provideRiversLocalData(RiversLocalData riverLocalData);*/

    @Singleton
    @Binds
    @Remote
    abstract RiversRemoteData provideRiversRemoteData(RiversRemoteData riversRemoteData);
}
