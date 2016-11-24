package com.rba.unittestmvp.login;

import android.util.Log;

import com.rba.unittestmvp.api.client.DemoApiManager;
import com.rba.unittestmvp.api.client.NetworkError;
import com.rba.unittestmvp.model.response.LoginResponse;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class LoginInteractor {

    public static void login(Map<String, String> data, final LoginCallback callback){

        Log.i("x- parameter", data.toString());

        /*
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
        */

        DemoApiManager.apiManager().login(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(new Func1<Throwable, Observable<? extends LoginResponse>>() {
                @Override
                public Observable<? extends LoginResponse> call(Throwable throwable) {
                    return Observable.error(throwable);
                }
            }).subscribe(new Subscriber<LoginResponse>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    callback.onFailure(new NetworkError(e));
                }

                @Override
                public void onNext(LoginResponse loginResponse) {
                    callback.onResponse(loginResponse);
                }
            });

    }

}
