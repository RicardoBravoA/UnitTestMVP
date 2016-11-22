package com.rba.unittestmvp.api;

import com.rba.unittestmvp.model.response.ErrorResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 16/11/16.
 */


public class ErrorUtil {

    public static ErrorResponse parseError(Response<?> response) {

        Converter<ResponseBody, ErrorResponse> converter = DemoApiManager.getRetrofit()
                .responseBodyConverter(ErrorResponse.class,
                        new Annotation[0]);

        ErrorResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponse();
        }

        return error;
    }

}
