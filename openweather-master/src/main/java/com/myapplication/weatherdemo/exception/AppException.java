package com.myapplication.weatherdemo.exception;

/**
 * Application exception for the weather demo
 */
public class AppException extends Exception {
    private Exception wrappedException;

    private String errorMessage;

    public AppException(String errorMessage) {
        super(errorMessage);
    }

    public AppException(Exception wrappedException, String errorMessage) {
        super(errorMessage);
        this.wrappedException = wrappedException;
        this.errorMessage = errorMessage;
    }
}
