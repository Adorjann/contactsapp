package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.service.exception.DuplicateDataException;
import com.adorjanpraksa.contactsapp.service.exception.ForbiddenAccessException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .data(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .message(exception.getMessage())
                        .build());

    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity<?> handleForbiddenAccessException(ForbiddenAccessException exception) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.builder()
                        .data(exception.getMessage())
                        .status(HttpStatus.FORBIDDEN)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<?> handleDuplicateDataException(DuplicateDataException exception) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .data(exception.getMessage())
                        .status(HttpStatus.CONFLICT)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .data("Something went wrong")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Something went wrong")
                        .build());
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorResponse<T> {

        private T data;
        private HttpStatus status;
        private String message;
    }
}
