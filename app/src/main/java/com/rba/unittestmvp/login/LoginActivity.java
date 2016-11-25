package com.rba.unittestmvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.rba.unittestmvp.R;
import com.rba.unittestmvp.product.ProductActivity;
import com.rba.unittestmvp.storage.SessionManager;
import com.rba.unittestmvp.util.Constant;
import com.rba.unittestmvp.util.Util;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private AppCompatEditText txtEmail;
    private AppCompatEditText txtPassword;
    private View viewLoading;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        txtEmail = (AppCompatEditText) findViewById(R.id.txtEmail);
        txtPassword = (AppCompatEditText) findViewById(R.id.txtPassword);
        AppCompatButton btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
        viewLoading = findViewById(R.id.viewLoading);

        hideProgress();

        loginPresenter = new LoginPresenter(this);
        validSession();

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(txtEmail.getText().toString())){
                    hideEmailError();
                }
            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(txtPassword.getText().toString())){
                    hidePasswordError();
                }
            }
        });

        btnLogin.setOnClickListener(this);


    }

    @Override
    public void showProgress() {
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        viewLoading.setVisibility(View.GONE);
    }

    @Override
    public void showPasswordError() {
        tilPassword.requestFocus();
        tilPassword.setErrorEnabled(true);
        tilPassword.setError(getString(R.string.error));
    }

    @Override
    public void hidePasswordError() {
        tilPassword.setErrorEnabled(false);
        tilPassword.setError("");
    }

    @Override
    public void showEmailError() {
        tilEmail.requestFocus();
        tilEmail.setErrorEnabled(true);
        if(Util.validEmail(txtEmail.getText().toString().trim())){
            tilEmail.setError(getString(R.string.error));
        }else{
            tilEmail.setError(getString(R.string.error_email));
        }

    }

    @Override
    public void hideEmailError() {
        tilEmail.setErrorEnabled(false);
        tilEmail.setError("");
    }

    @Override
    public void nextActivity() {
        startActivity(new Intent(this, ProductActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin){
            validateData();
        }
    }

    @Override
    public void showErrorMessage(String message) {
        hideProgress();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validSession() {
        if(SessionManager.isSession(this)){
            nextActivity();
        }
    }

    @Override
    public void validateData() {
        if(!loginPresenter.validEmail(txtEmail.getText().toString().trim())){
            showEmailError();
            return;
        }

        if(!loginPresenter.validPassword(txtPassword.getText().toString().trim())){
            showPasswordError();
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put(Constant.KEY_EMAIL, txtEmail.getText().toString().trim());
        data.put(Constant.KEY_PASSWORD, txtPassword.getText().toString().trim());

        loginPresenter.login(data);
    }

}
