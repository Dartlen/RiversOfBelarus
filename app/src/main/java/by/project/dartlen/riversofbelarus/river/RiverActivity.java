package by.project.dartlen.riversofbelarus.river;

import android.os.Bundle;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.Utils.ActivityUtils;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import ru.terrakok.cicerone.NavigatorHolder;


public class RiverActivity extends DaggerAppCompatActivity {

    @Inject
    NavigatorHolder navigatorHolder;

    @Inject
    RiverPresenter mRiversPresenter;

    @Inject
    Lazy<RiverFragment> riverFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rivers);

        RiverFragment x = (RiverFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (x == null) {
            x = riverFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), x, R.id.contentFrame,"sdfghjkl");
        }
    }

}
