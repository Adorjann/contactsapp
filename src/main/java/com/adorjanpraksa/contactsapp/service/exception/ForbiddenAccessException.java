package com.adorjanpraksa.contactsapp.service.exception;

public class ForbiddenAccessException extends RuntimeException {

    private final String message;

    public ForbiddenAccessException(String message) {
        super(message);
        this.message = message;
    }
}
