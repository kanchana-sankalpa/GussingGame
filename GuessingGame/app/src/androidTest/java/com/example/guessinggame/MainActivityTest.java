
package com.example.guessinggame;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Assert;
import org.junit.Rule;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

import com.example.guessinggame.MainActivity;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.StringContains.containsString;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void wheninputis1() {
        Assert.assertEquals(false, InputValidation.input(1));
    }
    // 1.3
    @Test
    public void wheninputisbetween1to100() {
        InputValidation.input(50);
        Assert.assertTrue(true);
    }
    // 1.4
    @Test
    public void wheninputisorabove100() {
        Assert.assertFalse(InputValidation.input(100));
    }
    // 1.5
    @Test(expected = RuntimeException.class)
    public void wheninputisstring() {
        Assert.assertEquals(false, InputValidation.input(Integer.parseInt("test")));
    }
    // 1.6
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
        onView(withId(R.id.editTxtGuessedNumber)).perform(typeText(String.valueOf(10)));
        onView(withId(R.id.btnGuess)).perform(click());
        onView(withId(R.id.txtViewDecision)).check(matches(withText(R.string.wrong)));
    }

    // 3.2
    @Test
    public void displaycorrectresult() {
        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.editTxtGuessedNumber)).perform(typeText(String.valueOf(50)));
        onView(withId(R.id.btnGuess)).perform(click());
        onView(withId(R.id.txtViewDecision)).check(matches(withText(R.string.correct)));
    }

    // 4.1
    @Test
    public void attemptnumberdisplaywhencorrectguess() {


        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.editTxtGuessedNumber)).perform(typeText(String.valueOf(10)));
        onView(withId(R.id.btnGuess)).perform(click());

        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.editTxtGuessedNumber)).perform(replaceText(String.valueOf(50)));
        onView(withId(R.id.btnGuess)).perform(click());


        onView(withId(R.id.txtViewNumberOfAttempts)).check(matches(withText("2")));

    }

    // 4.2
    @Test
    public void attemptnumberdisplaywhenquit() {


        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.editTxtGuessedNumber)).perform(replaceText(String.valueOf(10)));
        onView(withId(R.id.btnGuess)).perform(click());

        onView(withId(R.id.btnQuit)).perform(click());
        onView(withId(R.id.txtViewNumberOfAttempts)).check(matches(withText("1")));

    }



    // 5.1
    @Test
    public void guessbuttondisablewhencorrectguess() {

        mActivityRule.getActivity().randInt = 50;
        onView(withId(R.id.editTxtGuessedNumber)).perform(replaceText(String.valueOf(50)));
        onView(withId(R.id.btnGuess)).perform(click());
        IdlingPolicies.setMasterPolicyTimeout(10, TimeUnit.SECONDS);
        onView(withId(R.id.btnGuess)).check(matches(not(isClickable())));

    }


    // 5.2
    @Test
    public void guessbuttondisablewhenquit() {

        onView(withId(R.id.btnQuit)).perform(click());
        onView(withId(R.id.btnGuess)).check(matches(not(isClickable())));
        onView(withId(R.id.btnGuess)).check(matches(not(isClickable())));

    }

    // 6
    @Test
    public void applicationclosewhenclickquittwice() {

        onView(withId(R.id.btnQuit)).perform(click());
        onView(withId(R.id.btnQuit)).perform(click());

        assertTrue(mActivityRule.getActivity().isFinishing());
    }



}
