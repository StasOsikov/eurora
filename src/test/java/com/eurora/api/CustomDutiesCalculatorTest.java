package com.eurora.api;

import com.eurora.api.entities.request.CustomsCalculatorRequest;
import com.eurora.api.entities.response.CustomCalculatorResponse;
import com.eurora.api.entities.response.error.ErrorMessageResponse;
import com.eurora.api.extension.Smoke;
import com.eurora.api.service.CustomCalculatorComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class)
@Execution(ExecutionMode.CONCURRENT)
class CustomDutiesCalculatorTest {

    @Autowired
    private Environment environment;
    @Autowired
    CustomCalculatorComponent customCalculatorComponent;

    @Smoke
    @DisplayName("Unauthorized status code should be sent if api key is empty or incorrect")
    @ParameterizedTest
    @CsvSource(value = {":401", "\n:401", "6d8eef2a-4e03-4b7b-b25b-b18f0c84e016:401", "test:401", "401:401"}, delimiter = ':')
    void shouldReturnUnauthorizedStatusUsingIncorrectApiKey(String apiKey, int code){
        CustomsCalculatorRequest customsCalculator = CustomsCalculatorRequest.builder().build();
        Integer statusCode = customCalculatorComponent.getStatusCode(apiKey, getCustomsCalculatorRequest(customsCalculator));
        assertEquals((int) statusCode, code);
    }

    @Smoke
    @DisplayName("Violation constraint should be sent in a response if required field is empty or missed")
    @ParameterizedTest
    @MethodSource("getUserData")
    void shouldReturnErrorMessageIfRequiredFieldIsMissed(CustomsCalculatorRequest customsCalculator, String type, String message) {
        ErrorMessageResponse errorMessageResponse = customCalculatorComponent.getUserErrorMessage(getKey(), getCustomsCalculatorRequest(customsCalculator));
        Assertions.assertAll("Error message has correct message",
                () -> assertEquals(errorMessageResponse.getType(), type),
                () -> assertTrue(errorMessageResponse.getRows().stream().anyMatch(m -> m.getMessage().contains(message))));
    }

    @Smoke
    @DisplayName("Success status code should be returned if all data is correct")
    @ParameterizedTest
    @CsvSource({ "200, USD", "200, EUR"})
    void shouldReturnValidSuccessStatusCode(int statusCode, String currency){
        CustomsCalculatorRequest customsCalculator = CustomsCalculatorRequest.builder().orderCurrency(currency).build();
        Integer code = customCalculatorComponent.getStatusCode(getKey(), getCustomsCalculatorRequest(customsCalculator));
        assertEquals(statusCode, code);
    }

    @Smoke
    @DisplayName("Goods Response should be non null if all data is correct")
    @ParameterizedTest
    @CsvSource({ "1, USD", "1, EUR"})
    void shouldReturnNonNullGoodsResponse(int size, String currency){
        CustomsCalculatorRequest customsCalculator = CustomsCalculatorRequest.builder().orderCurrency(currency).build();
        List<CustomCalculatorResponse> customCalculatorResponse = customCalculatorComponent.getUserDuties(getKey(), getCustomsCalculatorRequest(customsCalculator));
        assertEquals(customCalculatorResponse.get(0).getGoods().size(), size);
    }

    @Smoke
    @DisplayName("Country Response should be correct if provided right country name")
    @ParameterizedTest
    @CsvSource({ "US", "UA"})
    void shouldReturnCorrectCountry(String country){
        CustomsCalculatorRequest customsCalculator = CustomsCalculatorRequest.builder().originCountry(country).build();
        List<CustomCalculatorResponse> customCalculatorResponse = customCalculatorComponent.getUserDuties(getKey(), getCustomsCalculatorRequest(customsCalculator));
        assertEquals(customCalculatorResponse.get(0).getOriginCountry(), country);
    }

    @Smoke
    @DisplayName("List of goods should be valid and non null")
    @ParameterizedTest
    @CsvSource({ "Fidget spinners, 0.0"})
    void shouldReturnValidGoodsValues(String description, double dutyRate) {
        CustomsCalculatorRequest customsCalculator = CustomsCalculatorRequest.builder().build();
        List<CustomCalculatorResponse> customCalculatorResponse = customCalculatorComponent.getUserDuties(getKey(), getCustomsCalculatorRequest(customsCalculator));
        Assertions.assertAll("Valid goods response",
                () -> assertTrue(customCalculatorResponse.get(0).getGoods().stream().anyMatch(item -> item.getDescription().equals(description))),
                () -> assertTrue(customCalculatorResponse.get(0).getGoods().stream().anyMatch(item -> item.getDuty().equals(dutyRate))));
    }

    private List<CustomsCalculatorRequest> getCustomsCalculatorRequest(CustomsCalculatorRequest customsCalculator){
        List<CustomsCalculatorRequest> customsCalculatorRequest = new ArrayList<>(1);
        addToList(customsCalculatorRequest, Stream.of(customsCalculator));
        return customsCalculatorRequest;
    }

    private static Stream<Arguments> getUserData() {
        return Stream.of(Arguments.of(CustomsCalculatorRequest.builder().orderCurrency("").build(), "CONSTRAINT_VIOLATION", "size must be between 3 and 3"),
                Arguments.of(CustomsCalculatorRequest.builder().originCountry("").build(), "CONSTRAINT_VIOLATION", "size must be between 2 and 2"),
                Arguments.of(CustomsCalculatorRequest.builder().destinationCountry("").build(), "CONSTRAINT_VIOLATION", "size must be between 2 and 2"),
                Arguments.of(CustomsCalculatorRequest.builder().destinationRegion("").build(), "CONSTRAINT_VIOLATION", "region code not found"),
                Arguments.of(CustomsCalculatorRequest.builder().additionalValueShare("").build(), "ARGUMENT_NOT_VALID", "Value not recognized (), please refer to specification for available values."),
                Arguments.of(CustomsCalculatorRequest.builder().transactionModel("").build(), "ARGUMENT_NOT_VALID", "Value not recognized (), please refer to specification for available values."));
    }

    private <T> List<T> addToList(List<T> target, Stream<T> source) {
        return source.collect(Collectors.toCollection(() -> target));
    }
    private String getKey(){
        byte[] decodedBytes = Base64.getDecoder().decode(environment.getProperty("api.key"));
        return new String(decodedBytes);
    }
}
