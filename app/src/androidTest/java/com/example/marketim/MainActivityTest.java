package com.example.marketim;

import com.example.marketim.Model.Market;
import com.example.marketim.View.MainActivity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void MainActivityLaunched() {
        onView(withText(R.string.Log_out)).check(matches(isDisplayed()));
    }

    @Test
    public void LogOutTested() {
        onView(withId(R.id.logOut)).perform(click());
        onView(withText(R.string.logingout_str)).check(matches(isDisplayed()));
        onView(withText(R.string.yes_str)).perform(click());
        onView(withText(R.string.SignIn)).check(matches(isDisplayed()));
        onView(withId(R.id.KeepMeSigned)).check(matches(isNotChecked()));
    }

    @Test
    public void ListViewPopulatedWithData() {

        onData(instanceOf(Market.class))
                .inAdapterView(allOf(withId(R.id.recycleListView), isDisplayed()));

    }
}
