package com.evseyev.personspringboot.controller;

import com.evseyev.personspringboot.exeptions.CreatedExeption;
import com.evseyev.personspringboot.exeptions.ErrorResponse;
import com.evseyev.personspringboot.exeptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse catchResourceNotFoundException(NotFoundException e) {
        log.error("NotFoundException. {} ", e.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CreatedExeption.class)
    public ErrorResponse handleCreatedExeption(CreatedExeption e) {
        log.error("CreatedExeption. {}", e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Incorrect JSON!!!!!! CreatedExeption " + e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException. {}", ex.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Incorrect JSON! HttpMessageNotReadableException " + ex.getMessage());
    }

//
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ConstraintViolationException.class)
//    protected ErrorResponse handleCreeeeeeeExeption(ConstraintViolationException ex) {
//        log.error("ConstraintViolationException. {}", ex.getMessage());
//        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
}

