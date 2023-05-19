package com.eurora.api.service;

import com.eurora.api.Service;
import com.eurora.api.ServiceType;
import com.eurora.api.entities.request.CustomsDutiesCalculatorRequest;
import com.eurora.api.entities.response.CustomCalculatorResponse;
import com.eurora.api.entities.response.error.ErrorMessageResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.List;

@Service(type = ServiceType.MICROSERVICE)
public interface CustomCalculatorService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json;charset=UTF-8"
    })
    @POST("customs-calculator/v1/shopping-cart")
    Call<List<CustomCalculatorResponse>> getUserDutiesResponse(@Header("X-Auth-Token") String key, @Body List<CustomsDutiesCalculatorRequest> customsDutiesCalculatorRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json;charset=UTF-8",
    })
    @POST("customs-calculator/v1/shopping-cart")
    Call<ResponseBody> getStatusCode(@Header("X-Auth-Token") String key, @Body List<CustomsDutiesCalculatorRequest> customsDutiesCalculatorRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json;charset=UTF-8",
    })
    @POST("customs-calculator/v1/shopping-cart")
    Call<ErrorMessageResponse> getErrorMessage(@Header("X-Auth-Token") String key, @Body List<CustomsDutiesCalculatorRequest> customsDutiesCalculatorRequest);
}
