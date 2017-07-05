package com.example.deepak.partyzing.View;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.deepak.partyzing.ProductActivity;
import com.example.deepak.partyzing.R;
import com.example.deepak.partyzing.Util.Utilities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.getIdlingResources;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.deepak.partyzing.Util.Utilities.firstChildOf;

import static com.example.deepak.partyzing.Util.Utilities.waitId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created on 2/8/16.
 */
@RunWith(AndroidJUnit4.class)
public class ProductActivityTest {
    ProductActivity productActivity;
    CountingIdlingResource idlingResource;
    @Rule
    public final ActivityTestRule<ProductActivity> pActivityRule =
            new ActivityTestRule<>(ProductActivity.class);

    @Before
    public void setup() throws Exception {
        productActivity=pActivityRule.getActivity();
        idlingResource=new CountingIdlingResource("BUTTON_LOADER");
        productActivity.setIdlingResource(idlingResource);
    }

    @Test
    public void isPartyzingLogoDisplayed(){
        onView(ViewMatchers.withId(R.id.image)).check(matches(isDisplayed()));
    }

    @Test
    public void isTitleTextViewDisplayed(){
        onView(ViewMatchers.withText(R.string.products)).check(matches(isDisplayed()));
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
    public void shudNotDisplayPriceWhenNoItemSelected(){
        onView(ViewMatchers.withId(R.id.price)).check(matches(not(isDisplayed())));
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
        onView(allOf(withId(R.id.flcontainer_one), isDescendantOfA(firstChildOf(withId(R.id.view_Pager))))).perform(click());
        onView(withId(R.id.next)).perform(click());
        //onView(withText(R.string.)).check(matches(isDisplayed()));
    }

    @Test
    public void shudUpdatePriceWhenItemSelected(){
        onView(allOf(withId(R.id.flcontainer_one), isDescendantOfA(firstChildOf(withId(R.id.view_Pager))))).perform(click());
        Espresso.registerIdlingResources(idlingResource);
        //getInstrumentation().waitForIdleSync();
        //onView(withId(R.id.price)).check(matches(withText(productActivity.getPrice())));
        onView(withId(R.id.price)).check(matches(isDisplayed()));
    }
}
