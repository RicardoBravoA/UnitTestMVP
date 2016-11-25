package com.rba.unittestmvp.util;

import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.google.gson.Gson;
import com.rba.unittestmvp.model.response.ErrorResponse;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class Util {

    private Util() {
        throw new IllegalAccessError("Util class");
    }

    public static boolean validEmailPattern(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean validEmail(String email){
        if(validEmailPattern(email.trim())){
            return true;
        }
        return false;
    }

    public static boolean validPassword(String password) {
        if(password.length()>5){
            return true;
        }
        return false;
    }

    public static boolean isError(String json){
        try{
            ErrorResponse errorResponse = new Gson().fromJson(json, ErrorResponse.class);
            Log.i("z- onResponse", new Gson().toJson(errorResponse));
            Log.i("z- getStatus", " - "+errorResponse.getStatus());
            return errorResponse.getStatus() != null;
        }catch (ClassCastException e){
            e.printStackTrace();
        }

        return false;
    }

    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {

            }

        };
    }

}
