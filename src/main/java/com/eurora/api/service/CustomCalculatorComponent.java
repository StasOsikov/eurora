package com.eurora.api.service;

import com.eurora.api.ServiceBuilder;
import com.eurora.api.entities.request.CustomsCalculatorRequest;
import com.eurora.api.entities.response.CustomCalculatorResponse;
import com.eurora.api.entities.response.error.ErrorMessageResponse;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    public List<CustomCalculatorResponse> getUserDuties(String key, List<CustomsCalculatorRequest> customsCalculatorRequest) {
        return calculatorService
                .getUserDutiesResponse(key, customsCalculatorRequest)
                .execute()
                .body();
    }

    @SneakyThrows
    public Integer getStatusCode(String key, List<CustomsCalculatorRequest> customsCalculatorRequest) {
        return calculatorService.getStatusCode(key, customsCalculatorRequest).execute().code();
    }

    @SneakyThrows
    public ErrorMessageResponse getUserErrorMessage(String key, List<CustomsCalculatorRequest> customsCalculatorRequest) {
        return calculatorService
                .getErrorMessage(key, customsCalculatorRequest)
                .execute()
                .body();
    }
}