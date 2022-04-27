package com.example.mssubscribe.service;

import com.example.mssubscribe.dao.entity.QSubscribeEntity;
import com.example.mssubscribe.dao.repository.SubscribeQRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeQService {

    private final SubscribeQRepository repository;
    private final SubscribeService service;


    public void addschedulerMonthlyPayment(String name, String category, String apiKey){

      QSubscribeEntity entity= QSubscribeEntity.builder()
                .topicName(name)
                .topicCategory(category)
                .apiKey(apiKey)
                .build();

        repository.save(entity);
    }

    public void schedulerMonthlyPayment(){

        var entity= repository.findAll();
        for (QSubscribeEntity en: entity){
            log.info("Action.Log monthlyPayment start with apiKey {}",en.getApiKey());
            service.createSubscribeWithBalance(en.getTopicName(),en.getTopicCategory(), en.getApiKey());
            log.info("Action.Log monthlyPayment end with apiKey {}",en.getApiKey());

        }
    }

}
