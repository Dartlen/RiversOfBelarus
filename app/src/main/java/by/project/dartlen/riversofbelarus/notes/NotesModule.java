package by.project.dartlen.riversofbelarus.notes;

import butterknife.BindView;
import by.project.dartlen.riversofbelarus.di.scopes.ActivityScope;
import by.project.dartlen.riversofbelarus.di.scopes.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 * Created by Dartlen on 17.12.2017.
 */
@Module
public abstract class NotesModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract NotesFragment notesFragment();

    @ActivityScope
    @Binds abstract NotesContract.Presenter notesPresenter(NotesPresenter presenter);
}
