package com.example.marketim;

import com.example.marketim.View.LoginActivity.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)
public class LogInTest {

    @Rule
    public final ActivityTestRule<Login> main = new ActivityTestRule<>(Login.class);

    @Test
    public void LoginAuth(){
        onView(withId(R.id.UserName)).perform(typeText("kariyer"));
        onView(withId(R.id.Password)).perform(typeText("2019ADev"));
        closeSoftKeyboard();
        onView(withId(R.id.KeepMeSigned)).check(matches(isNotChecked())).perform(click());
        onView(withId(R.id.logIn)).perform(click());






    }
}
