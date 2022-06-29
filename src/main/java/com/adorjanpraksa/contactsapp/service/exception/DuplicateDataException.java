package com.adorjanpraksa.contactsapp.service.exception;

public class DuplicateDataException extends RuntimeException {

    private final String message;

    public DuplicateDataException(String message) {
        super(message);
        this.message = message;
    }
}
