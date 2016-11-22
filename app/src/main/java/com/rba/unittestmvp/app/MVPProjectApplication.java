package com.rba.unittestmvp.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class MVPProjectApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MVPProjectApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MVPProjectApplication.context;
    }
}