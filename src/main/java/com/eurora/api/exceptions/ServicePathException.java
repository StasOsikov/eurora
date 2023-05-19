package com.eurora.api.exceptions;

public class ServicePathException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Service path is not present or doesn't end with slash";
    }
}
