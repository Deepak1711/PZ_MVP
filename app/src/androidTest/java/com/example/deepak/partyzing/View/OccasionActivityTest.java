package com.example.deepak.partyzing.View;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.FrameLayout;

import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.CoreMatchers.allOf;
import static android.support.test.espresso.DataInteraction.*;


import com.example.deepak.partyzing.OccasionActivity;
import com.example.deepak.partyzing.R;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static com.example.deepak.partyzing.Util.Utilities.*;

/**
 * Created on 1/8/16.
 */
@RunWith(AndroidJUnit4.class)
public class OccasionActivityTest {
    @Rule
    public final ActivityTestRule<OccasionActivity> oActivityRule =
            new ActivityTestRule<>(OccasionActivity.class);

    @Test
    public void isPartyzingLogoDisplayed(){
        onView(ViewMatchers.withId(R.id.image)).check(matches(isDisplayed()));
    }

    @Test
    public void isTitleTextViewDisplayed(){
        onView(ViewMatchers.withText(R.string.occasion)).check(matches(isDisplayed()));
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
    public void shudStartThemeActivityWhenNextBtnIsClicked(){
        onView(Matchers.allOf(withId(R.id.flcontainer_one), isDescendantOfA(firstChildOf(withId(R.id.view_Pager))))).perform(click());
        onView(withId(R.id.next)).perform(click());
        onView(withText(R.string.partyTheme)).check(matches(isDisplayed()));
    }
}