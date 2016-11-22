package com.rba.unittestmvp.login;

import android.util.Log;

import com.rba.unittestmvp.api.DemoApiManager;
import com.rba.unittestmvp.api.ErrorUtil;
import com.rba.unittestmvp.model.response.ErrorResponse;
import com.rba.unittestmvp.model.response.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class LoginInteractor {

    public static void login(Map<String, String> data, final LoginCallback callback){

        Log.i("x- parameter", data.toString());

        Call<LoginResponse> call = DemoApiManager.apiManager().login(data);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body());
                }else{
                    ErrorResponse error = ErrorUtil.parseError(response);
                    callback.onFailure(error.get_meta().getStatus());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

}
