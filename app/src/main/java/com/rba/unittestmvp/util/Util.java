package com.rba.unittestmvp.util;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class Util {
    public static boolean validEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
