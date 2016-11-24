package com.rba.unittestmvp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rba.unittestmvp.product.ProductActivity;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Assert.assertEquals("com.rba.unittestmvp", appContext.getPackageName());
    }


    @Rule
    public ActivityTestRule<ProductActivity> mActivityRule = new ActivityTestRule<>(ProductActivity.class);


    @Test
    public void onClickItem0() {
        Espresso.onView(ViewMatchers.withId(R.id.rcvProduct))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

    }

    @Test
    public void onClickItem1() {
        Espresso.onView(ViewMatchers.withId(R.id.rcvProduct))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));

    }


}
