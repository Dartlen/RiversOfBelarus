package by.project.dartlen.riversofbelarus.rivers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.postinfo.PostInfoFragment;
import by.project.dartlen.riversofbelarus.posts.PostsFragment;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;


public class RiverActivity extends DaggerAppCompatActivity {

    @Inject
    NavigatorHolder navigatorHolder;

    @Inject
    RiverPresenter mRiversPresenter;

    @Inject
    Lazy<RiverFragment> riverFragmentProvider;

    @Inject
    Lazy<PostsFragment> postsFragmentProvider;

    @Inject
    Lazy<PostInfoFragment> postInfoFragmentProvider;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.contentFrame) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            if(screenKey.equals("riverFragment"))
                return riverFragmentProvider.get();
            else if(screenKey.equals("postsFragment")) {
                postsFragmentProvider.get().setRiver(data);
                return postsFragmentProvider.get();
            }else if(screenKey.equals("postInfoFragment")){
                return postInfoFragmentProvider.get();
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(RiverActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }

        @Override
        public void applyCommand(Command command) {
            super.applyCommand(command);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rivers);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.exit));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            navigator.applyCommand(new Replace("riverFragment", 1));
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }
}
