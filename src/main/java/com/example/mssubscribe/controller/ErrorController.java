package com.example.mssubscribe.controller;

import com.example.mssubscribe.model.dto.ExceptionDto;
import com.example.mssubscribe.model.exception.ClientException;
import com.example.mssubscribe.model.exception.SubscribeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {


    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionDto handle(ClientException e){
        return new ExceptionDto(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(SubscribeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(SubscribeException e){
        return new ExceptionDto(e.getCode(), e.getMessage());
    }
}
