package by.project.dartlen.riversofbelarus.rivers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import javax.inject.Inject;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.main.MainFragment;
import by.project.dartlen.riversofbelarus.notes.NotesFragment;
import by.project.dartlen.riversofbelarus.postinfo.PostInfoFragment;
import by.project.dartlen.riversofbelarus.posts.PostsFragment;
import by.project.dartlen.riversofbelarus.signin.SigninFragment;
import by.project.dartlen.riversofbelarus.signup.SignupFragment;
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
    Lazy<MainFragment> mainFragmentProvider;

    @Inject
    Lazy<RiverFragment> riverFragmentProvider;

    @Inject
    Lazy<PostsFragment> postsFragmentProvider;

    @Inject
    Lazy<PostInfoFragment> postInfoFragmentProvider;

    @Inject
    Lazy<SigninFragment> signInFragmentProvider;

    @Inject
    Lazy<SignupFragment> signUpFragmentProvider;

    @Inject
    Lazy<NotesFragment> notesFragmentProvider;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.contentFrame) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            if(screenKey.equals("mainFragment"))
                return mainFragmentProvider.get();
            else if(screenKey.equals("signInFragment"))
                return signInFragmentProvider.get();
            else if(screenKey.equals("signUpFragment"))
                return signUpFragmentProvider.get();
            else if(screenKey.equals("riverFragment"))
                return riverFragmentProvider.get();
            else if(screenKey.equals("postsFragment")) {
                postsFragmentProvider.get().setRiver(data);
                return postsFragmentProvider.get();
            }else if(screenKey.equals("postInfoFragment")){
                postInfoFragmentProvider.get().setPost(data);
                return postInfoFragmentProvider.get();
            }else if(screenKey.equals("notesFragment")){
                notesFragmentProvider.get().setNotes(data);
                return notesFragmentProvider.get();
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
            navigator.applyCommand(new Replace("mainFragment", 1));
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
