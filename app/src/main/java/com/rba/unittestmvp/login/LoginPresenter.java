package com.rba.unittestmvp.login;

import android.util.Log;

import com.rba.unittestmvp.R;
import com.rba.unittestmvp.api.client.NetworkError;
import com.rba.unittestmvp.app.MVPProjectApplication;
import com.rba.unittestmvp.model.response.LoginResponse;
import com.rba.unittestmvp.storage.SessionManager;
import com.rba.unittestmvp.util.Constant;
import com.rba.unittestmvp.util.Util;

import java.util.Map;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class LoginPresenter implements LoginCallback {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void login(Map<String, String> data) {
        loginView.showProgress();
        LoginInteractor.login(data, this);
    }

    public boolean validEmail(String email){
        if (Util.validEmail(email)) {
            return true;
        }
        return false;
    }

    public boolean validPassword(String password){
        if (Util.validPassword(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void onResponse(LoginResponse loginResponse) {
        loginView.hideProgress();
        if(loginResponse.get_meta().getStatus().equals(Constant.KEY_SUCCESS)){
            SessionManager.addSession(MVPProjectApplication.getAppContext(),
                    loginResponse.getData().get(0));
            loginView.nextActivity();
        }else{
            loginView.showErrorMessage(MVPProjectApplication.getAppContext().getString(R.string.message_login));
        }
    }

    @Override
    public void onFailure(NetworkError networkError) {
        Log.i("x- onError", networkError.getAppErrorMessage());
        loginView.hideProgress();
        loginView.showErrorMessage(networkError.getAppErrorMessage());
    }

}
