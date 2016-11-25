package com.rba.unittestmvp;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rba.unittestmvp.login.LoginActivity;
import com.rba.unittestmvp.util.Util;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.JVM)
public class LoginActivityUITest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);
    private String email = "ricardobravo@outlook.com";
    private String password = "123456";

    @Test
    public void addEmail() {
        Espresso.onView(ViewMatchers.withId(R.id.txtEmail))
                .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.txtEmail)).check(ViewAssertions.matches(ViewMatchers.withText(email)));
    }

    @Test
    public void addPassword() {
        Espresso.onView(ViewMatchers.withId(R.id.txtPassword))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.txtPassword)).check(ViewAssertions.matches(ViewMatchers.withText(password)));
    }

    @Test
    public void addPasswordInvalid(){
        String password = "123";
        Espresso.onView(ViewMatchers.withId(R.id.txtEmail))
                .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.txtPassword))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.tilPassword))
                .check(ViewAssertions.matches(Util.hasTextInputLayoutErrorText(
                        InstrumentationRegistry.getTargetContext().getString(R.string.error))));
    }

    @Test
    public void addEmailInvalid(){
        String email = "ricardobravo";
        Espresso.onView(ViewMatchers.withId(R.id.txtEmail))
                .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.tilEmail))
                .check(ViewAssertions.matches(Util.hasTextInputLayoutErrorText(
                        InstrumentationRegistry.getTargetContext().getString(R.string.error_email))));
    }

    @Test
    public void login(){
        Espresso.onView(ViewMatchers.withId(R.id.txtEmail))
                .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.txtEmail)).check(ViewAssertions.matches(ViewMatchers.withText(email)));
        Espresso.onView(ViewMatchers.withId(R.id.txtPassword))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.txtPassword)).check(ViewAssertions.matches(ViewMatchers.withText(password)));
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
                .perform(ViewActions.click());
    }

}
