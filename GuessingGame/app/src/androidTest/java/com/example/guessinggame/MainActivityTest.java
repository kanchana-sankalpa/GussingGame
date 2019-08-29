
package com.example.guessinggame;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import com.example.guessinggame.MainActivity;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    // 1.1
    @Test
    public void wheninputis0() {
        Assert.assertEquals(false, InputValidation.input(0));
    }

    // 1.2
    @Test
    public void wheninputisbetween0to100() {
        InputValidation.input(50);
        Assert.assertTrue(true);
    }

    // 1.3
    @Test
    public void wheninputisorabove100() {
        Assert.assertFalse(InputValidation.input(100));

    }

    // 1.4
    @Test(expected = RuntimeException.class)
    public void wheninputisempty() {
        Assert.assertEquals(false, InputValidation.input(Integer.parseInt("")));

    }

    // 2.1
    @Test
    public void inputevaluationwrongguess() {

        Assert.assertFalse(InputEvaluation.evaluate(10, 50));

    }

    // 2.2
    @Test
    public void inputevaluationcorrectguess() {

        Assert.assertTrue(InputEvaluation.evaluate(50, 50));


    }

    // 3.1
    @Test
    public void displaywrongresult() {


        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.number)).perform(typeText(String.valueOf(10)));
        onView(withId(R.id.guess)).perform(click());
        onView(withId(R.id.correct)).check(matches(withText(R.string.wrong)));

    }

    // 3.2
    @Test
    public void displaycorrectresult() {


        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.number)).perform(typeText(String.valueOf(50)));
        onView(withId(R.id.guess)).perform(click());
        onView(withId(R.id.correct)).check(matches(withText(R.string.correct)));

    }

    // 4.1
    @Test
    public void attemptnumber() {


        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.number)).perform(typeText(String.valueOf(50)));
        onView(withId(R.id.guess)).perform(click());


        onView(mActivityRule.getActivity().attemptno)
    }


}
