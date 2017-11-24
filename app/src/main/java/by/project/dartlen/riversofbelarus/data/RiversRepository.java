package by.project.dartlen.riversofbelarus.data;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
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
    private HashSet<String> listRivers = new HashSet<String>();
    @Inject
    RiversRepository(@Remote RiversRemoteData riversRepository){ //
        mRiversRemoteData = riversRepository;
    }

    public void getRivers(final @NotNull LoadRiversCallback callback){
        mRiversRemoteData.getPosts(new LoadPostsCallback() {
            @Override
            public void onRiversLoaded(List<Post> riversList) {
                for(Post x:riversList)
                    listRivers.add(x.getRiver());
                callback.onRiversLoaded(listRivers);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callback.onDataNotAvailable(error);
            }
        });
    }
}
