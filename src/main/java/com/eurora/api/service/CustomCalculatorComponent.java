package com.eurora.api.service;

import com.eurora.api.ServiceBuilder;
import com.eurora.api.entities.request.CustomsDutiesCalculatorRequest;
import com.eurora.api.entities.response.CustomCalculatorResponse;
import com.eurora.api.entities.response.error.ErrorMessageResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.List;

@Log4j2
@Component
public class CustomCalculatorComponent {

    @Autowired
    private ServiceBuilder serviceBuilder;

    private CustomCalculatorService calculatorService;

    @PostConstruct
    public void init() {
        calculatorService = serviceBuilder
                .build(CustomCalculatorService.class);
    }

    @SneakyThrows
    public List<CustomCalculatorResponse> getUserDuties(String key, List<CustomsDutiesCalculatorRequest> customsDutiesCalculatorRequest) {
        return calculatorService
                .getUserDutiesResponse(key, customsDutiesCalculatorRequest)
                .execute()
                .body();
    }

    @SneakyThrows
    public Integer getStatusCode(String key, List<CustomsDutiesCalculatorRequest> customsDutiesCalculatorRequest) {
        return calculatorService.getStatusCode(key, customsDutiesCalculatorRequest).execute().code();
    }

    @SneakyThrows
    public ErrorMessageResponse getUserErrorMessage(String key, List<CustomsDutiesCalculatorRequest> customsDutiesCalculatorRequest) {
        Gson gson = new Gson();
        Type type = new TypeToken<ErrorMessageResponse>() {}.getType();
        return gson.fromJson(calculatorService
                .getErrorMessage(key, customsDutiesCalculatorRequest)
                .execute()
                .errorBody().charStream(),type);
    }
}