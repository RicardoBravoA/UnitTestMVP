package com.rba.unittestmvp.login;

import com.rba.unittestmvp.model.response.LoginResponse;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public interface LoginCallback {

    void onResponse(LoginResponse loginResponse);

    void onFailure(String error);

}
