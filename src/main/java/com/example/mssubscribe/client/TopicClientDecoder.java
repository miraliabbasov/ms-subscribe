package com.example.mssubscribe.client;

import com.example.mssubscribe.model.dto.ExceptionDto;
import com.example.mssubscribe.model.exception.ClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class TopicClientDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionDto exceptionDto ;


        try {
            exceptionDto = new ObjectMapper().readValue(response.body().asInputStream(),ExceptionDto.class);
        } catch (IOException e) {
        log.error("Action.start.Client exception {}",e.getMessage());
        throw new ClientException("500","UNEXPECT_ERROR");
        }

        return new ClientException(exceptionDto.getCode(),String.valueOf(response.status()));
    }
}
