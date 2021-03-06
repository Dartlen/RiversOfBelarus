package by.project.dartlen.riversofbelarus.data;

import java.util.HashSet;
import java.util.List;

import by.project.dartlen.riversofbelarus.data.remote.Post;

/***
 * Created by Dartlen on 24.11.2017.
 */

public interface LoadPostsCallback {
    void onRiversLoaded(HashSet<String> riversList);

    void onDataNotAvailable(String error);
}
