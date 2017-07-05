package com.example.deepak.partyzing.View;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.deepak.partyzing.R;
import com.example.deepak.partyzing.ThemeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.deepak.partyzing.Util.Utilities.firstChildOf;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created on 2/8/16.
 */
@RunWith(AndroidJUnit4.class)
public class ThemeActivityTest {
    @Rule
    public final ActivityTestRule<ThemeActivity> tActivityRule =
            new ActivityTestRule<>(ThemeActivity.class);

    @Test
    public void isPartyzingLogoDisplayed(){
        onView(ViewMatchers.withId(R.id.image)).check(matches(isDisplayed()));
    }

    @Test
    public void isTitleTextViewDisplayed(){
        onView(ViewMatchers.withText(R.string.partyTheme)).check(matches(isDisplayed()));
    }

    @Test
    public void isViewPagerDisplayed(){
        onView(ViewMatchers.withId(R.id.view_Pager)).check(matches(isDisplayed()));
    }

    @Test
    public void shudNotDisplayNextButtonWhenNoItemSelected(){
        onView(ViewMatchers.withId(R.id.next)).check(matches(not(isDisplayed())));
    }

    @Test
    public void shudDisplayNextButtonOnItemSelected(){
        //onView(anyOf(withParent(withId(R.id.view_Pager)),is(instanceOf(FrameLayout.class)))).perform(click());
        //onView(withId(R.id.view_Pager)).check(matches(hasDescendant(withId(R.id.flcontainer_one)))).perform(click());
        onView(allOf(withId(R.id.flcontainer_one), isDescendantOfA(firstChildOf(withId(R.id.view_Pager))))).perform(click());
        onView(withId(R.id.next)).check(matches(isDisplayed()));
    }

    @Test
    public void shudStartProductActivityWhenNextBtnIsClicked(){
        onView(allOf(withId(R.id.flcontainer_one), isDescendantOfA(firstChildOf(withId(R.id.view_Pager))))).perform(click());
        onView(withId(R.id.next)).perform(click());
        onView(withText(R.string.products)).check(matches(isDisplayed()));
    }
}
