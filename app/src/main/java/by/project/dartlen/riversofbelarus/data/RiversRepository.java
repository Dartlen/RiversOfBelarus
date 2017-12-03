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
    private HashSet<String> listRivers = new HashSet<>();
    private HashSet<String> listPosts = new HashSet<>();

    @Inject
    RiversRepository(@Remote RiversRemoteData riversRepository){
        mRiversRemoteData = riversRepository;
    }

    public void getRivers(final @NotNull LoadRiversCallback callback){
        mRiversRemoteData.getPosts(new LoadDataCallback() {
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

    public void getPosts(final @NotNull LoadPostsCallback callback, final String riverName){
        mRiversRemoteData.getPosts(new LoadDataCallback() {
            @Override
            public void onRiversLoaded(List<Post> riversList) {
                listPosts.clear();
                for(Post x:riversList)
                    if(x.getRiver().equals(riverName))
                        listPosts.add(x.getCity());
                callback.onRiversLoaded(listPosts);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callback.onDataNotAvailable(error);
            }
        });
    }

    public void getPostData(final @NotNull LoadPostDataCallback callback, final String post){
        mRiversRemoteData.getPosts(new LoadDataCallback() {
            @Override
            public void onRiversLoaded(List<Post> riversList) {
                for(Post x: riversList)
                    if(x.getCity().equals(post))
                        callback.onPostDataLoaded(x);
            }

            @Override
            public void onDataNotAvailable(String error) {
                    callback.onDataNotAvailable(error);
            }
        });
    }
}
