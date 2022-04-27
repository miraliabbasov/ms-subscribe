package com.example.mssubscribe.controller;

import com.example.mssubscribe.dao.entity.QSubscribeEntity;
import com.example.mssubscribe.dao.repository.SubscribeQRepository;
import com.example.mssubscribe.dao.repository.SubscribeRepository;
import com.example.mssubscribe.model.dto.SubscribeDto;
import com.example.mssubscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/subscribe")
public class SubscribeController {

    private final SubscribeRepository subscribeRepository;
    private final SubscribeService service;
    private final SubscribeQRepository repository;
    @GetMapping("/buy-topics/card")
    public SubscribeDto get(
            @RequestHeader String apiKey,
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam String cardNumber
    ) {

        return service.createSubscribe(name, category, apiKey, cardNumber);
    }

    @GetMapping("/buy-topics/balance")
    public SubscribeDto get(
            @RequestHeader String apiKey,
            @RequestParam String name,
            @RequestParam String category
    ) {

        return service.createSubscribeWithBalance(name, category, apiKey);
    }

    @GetMapping()
    public List<QSubscribeEntity> a(){
        log.info("findall called");
        return repository.findAll();
    }
}
