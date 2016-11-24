package com.rba.unittestmvp.util;

import android.util.Log;
import android.util.Patterns;

import com.google.gson.Gson;
import com.rba.unittestmvp.model.response.ErrorResponse;

import java.util.regex.Pattern;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class Util {

    private Util() {
        throw new IllegalAccessError("Util class");
    }

    public static boolean validEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
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

}
