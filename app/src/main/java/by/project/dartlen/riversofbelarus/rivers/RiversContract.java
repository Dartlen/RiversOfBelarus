package by.project.dartlen.riversofbelarus.rivers;

import java.util.HashSet;

import by.project.dartlen.riversofbelarus.BasePresenter;
import by.project.dartlen.riversofbelarus.BaseView;

/***
 * Created by Dartlen on 12.11.2017.
 */

public interface RiversContract {
    interface Presenter extends BasePresenter<View>{
        void loadRivers(HashSet<String> list);
        void onToolbarExitClicked();
    }

    interface View extends BaseView<Presenter>{
        void showRivers(HashSet<String> list);
    }
}
