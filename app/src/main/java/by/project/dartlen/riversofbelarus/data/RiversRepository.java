package by.project.dartlen.riversofbelarus.data;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.project.dartlen.riversofbelarus.data.remote.Post;
import by.project.dartlen.riversofbelarus.data.remote.RiversRemoteData;

import static com.google.common.base.Preconditions.checkNotNull;

/***
 * Created by Dartlen on 13.11.2017.
 */
@Singleton
public class RiversRepository {

    final private RiversRemoteData mRiversRemoteData;

    @Inject
    RiversRepository(@Remote RiversRemoteData riversRepository){ //
        mRiversRemoteData = riversRepository;
    }

    public void getPosts(final @NotNull LoadRiversCallback callback){
        mRiversRemoteData.getPosts(new LoadRiversCallback() {
            @Override
            public void onRiversLoaded(List<Post> riversList) {
                callback.onRiversLoaded(riversList);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callback.onDataNotAvailable(error);
            }
        });
    }
}
