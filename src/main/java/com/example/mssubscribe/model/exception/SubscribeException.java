package com.example.mssubscribe.model.exception;

import lombok.Getter;

@Getter
public class SubscribeException extends RuntimeException{
    private  final String code;

    public SubscribeException(String code,String message) {
        super(message);
        this.code=code;
    }

}
