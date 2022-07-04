package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.service.exception.DuplicateDataException;
import com.adorjanpraksa.contactsapp.service.exception.ForbiddenAccessException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .data(argumentNotValidExceptionErrorMessage(exception))
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Data validation failed")
                        .build());
    }

    private Map<String, String> argumentNotValidExceptionErrorMessage(MethodArgumentNotValidException exception) {

        return exception.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));

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
