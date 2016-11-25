package com.rba.unittestmvp.login;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public interface LoginView {

    void showProgress();

    void hideProgress();

    void showErrorMessage(String message);

    void showEmailError();

    void hideEmailError();

    void showPasswordError();

    void hidePasswordError();

    void nextActivity();

    void validSession();

    void validateData();

}
