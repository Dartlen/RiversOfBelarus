package by.project.dartlen.riversofbelarus.data;

import java.util.List;

import by.project.dartlen.riversofbelarus.data.remote.Post;

/***
 * Created by Dartlen on 02.12.2017.
 */

public interface LoadPostDataCallback {
    void onPostDataLoaded(Post postData);

    void onDataNotAvailable(String error);
}
