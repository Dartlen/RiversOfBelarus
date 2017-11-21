package by.project.dartlen.riversofbelarus;

/***
 * Created by Dartlen on 12.11.2017.
 */

public interface BasePresenter<T> {

    void takeView(T view);
    void dropView();
}
