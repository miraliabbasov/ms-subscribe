package com.example.mssubscribe.model.exception;


import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

    private  final String code;

    public ClientException(String code,String message) {
        super(message);
        this.code=code;
    }


}
