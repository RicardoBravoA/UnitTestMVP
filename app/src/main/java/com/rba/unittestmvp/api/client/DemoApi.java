package com.rba.unittestmvp.api.client;

import com.rba.unittestmvp.BuildConfig;
import com.rba.unittestmvp.model.response.LoginResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public interface DemoApi {

    @FormUrlEncoded
    @POST(BuildConfig.USER_LOGIN)
    Observable<LoginResponse> login(@FieldMap Map<String, String> data);

    @GET(BuildConfig.URL_PRODUCT)
    Observable<Object> product();
}
