package java.com.mvp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.com.mvp.base.AbstractTest;

import by.project.dartlen.riversofbelarus.R;
import by.project.dartlen.riversofbelarus.rivers.RiverActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/***
 * Created by Dartlen on 31.12.2017.
 */

@RunWith(AndroidJUnit4.class)
public class AuthTest extends AbstractTest {

    @Rule
    public ActivityTestRule<RiverActivity> mActivityRule = new ActivityTestRule<RiverActivity>(RiverActivity.class);

    @Test
    public void testMainElementsFragment() throws InterruptedException{
        //check displayed
        onView(withId(R.id.main_fragment)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSignUp)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSignIn)).check(matches(isDisplayed()));
        onView(withId(R.id.logo_main)).check(matches(isDisplayed()));
    }

    @Test
    public void testMainFragmentSingInButton() throws InterruptedException{
        onView(withId(R.id.btnSignIn)).perform(click());
    }

    @Test
    public void testMainFragmentSingUpButton() throws InterruptedException{
        onView(withId(R.id.btnSignUp)).perform(click());
    }

    @Test
    public void loginWithSuccessfully() throws InterruptedException{
        onView(withId(R.id.btnSignIn)).perform(click());
    }

    @Test
    public void loginWithErrorMessageToast() throws InterruptedException{
        onView(withId(R.id.btnSignIn)).perform(click());
        onView(withId(R.id.editPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.buttonSignIn)).perform(click());
        onView(withText("Ошибка входа, неверный номер или пароль!"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

}
