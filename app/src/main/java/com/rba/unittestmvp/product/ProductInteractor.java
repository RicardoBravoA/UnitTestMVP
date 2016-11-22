package com.rba.unittestmvp.product;

import com.rba.unittestmvp.api.DemoApiManager;
import com.rba.unittestmvp.api.ErrorUtil;
import com.rba.unittestmvp.model.response.ErrorResponse;
import com.rba.unittestmvp.model.response.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 21/11/16.
 */


public class ProductInteractor {

    public static void product(final ProductCallback callback){

        Call<ProductResponse> call = DemoApiManager.apiManager().product();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body());
                }else{
                    ErrorResponse error = ErrorUtil.parseError(response);
                    callback.onFailure(error.get_meta().getStatus());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

}
