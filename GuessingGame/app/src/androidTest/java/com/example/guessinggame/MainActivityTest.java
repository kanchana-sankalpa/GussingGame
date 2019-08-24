
package com.example.guessinggame;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    @Test(expected = RuntimeException.class)
    public void wheninputis0() {
        MainActivity.input(0);
    }

    @Test
    public void wheninputisbetweenoto100() {
        MainActivity.input(50);
        Assert.assertTrue(true);
    }

    @Test(expected = RuntimeException.class)
    public void wheninputisorabove100() {
        MainActivity.input(100);

    }

    @Test(expected = RuntimeException.class)
    public void wheninputisempty() {
        Assert.assertEquals(0, MainActivity.input(Integer.parseInt("")));

    }

}
