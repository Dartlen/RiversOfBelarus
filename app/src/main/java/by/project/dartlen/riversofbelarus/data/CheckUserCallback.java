package by.project.dartlen.riversofbelarus.data;

import by.project.dartlen.riversofbelarus.data.remote.User;

/***
 * Created by Dartlen on 10.12.2017.
 */

public interface CheckUserCallback {
    void onUserExist(boolean value);

    void onDataNotAvailable(String error);
}
