package com.example.mssubscribe.model.schedlock;

import com.example.mssubscribe.service.SubscribeQService;
import com.example.mssubscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscribeShedlock {
private final SubscribeService service;
private final SubscribeQService qService;


    @Scheduled(fixedDelayString = "PT1M")
    public void updateStatus(){
        service.schedulerStatusInactive();
    }

    @Scheduled(fixedDelayString = "PT1M")
    public void updateMonthlyPayment(){
        qService.schedulerMonthlyPayment();
    }

}
