package com.example.movement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGenericException(Exception ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotFoundException(IllegalArgumentException ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()).build();
    }

    @ExceptionHandler(ExceedsBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleExceedsBalanceException(ExceedsBalanceException ex) {
        return ErrorResponse.builder(ex,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()).build();
    }
}
