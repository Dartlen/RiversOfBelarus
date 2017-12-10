package by.project.dartlen.riversofbelarus.data;

import java.util.List;

import by.project.dartlen.riversofbelarus.data.remote.User;

/***
 * Created by Dartlen on 10.12.2017.
 */

public interface LoadUserCallback {
    void onUserLoaded(User user);

    void onDataNotAvailable(String error);
}
