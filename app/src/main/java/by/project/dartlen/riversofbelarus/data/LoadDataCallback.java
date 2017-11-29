package by.project.dartlen.riversofbelarus.data;

import java.util.List;

import by.project.dartlen.riversofbelarus.data.remote.Post;

/***
 * Created by Dartlen on 25.11.2017.
 */

public interface LoadDataCallback {
    void onRiversLoaded(List<Post> riversList);

    void onDataNotAvailable(String error);
}
