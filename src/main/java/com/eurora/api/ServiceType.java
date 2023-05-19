package com.eurora.api;

import lombok.Getter;

@Getter
public enum ServiceType {

    MICROSERVICE("https://api.integration.eurora.com/");

    private final String baseUrl;

    ServiceType(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
