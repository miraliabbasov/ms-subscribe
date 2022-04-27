package com.example.mssubscribe.service;

import com.example.mssubscribe.client.PaymentCardDto;
import com.example.mssubscribe.client.PaymentClient;
import com.example.mssubscribe.client.TopicClient;
import com.example.mssubscribe.dao.entity.SubscribeEntity;
import com.example.mssubscribe.dao.repository.SubscribeQRepository;
import com.example.mssubscribe.dao.repository.SubscribeRepository;
import com.example.mssubscribe.model.dto.SubscribeDto;
import com.example.mssubscribe.model.enums.Status;
import com.example.mssubscribe.model.exception.SubscribeException;
import com.example.mssubscribe.model.map.SubscribeMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeService {

    private final PaymentClient paymentClient;
    private final SubscribeRepository subscribeRepository;
    private final TopicClient topicClient;

    public SubscribeDto createSubscribe(String name, String category, String apiKey, String cardNumber) {

        PaymentCardDto cardDto = new PaymentCardDto();
        cardDto.setCardNumber(cardNumber);

        var subscribeEntity = get(name, category, apiKey);

        var amount = topicClient.getTopicAmount(name, category);

        int sum = 0;
        var counts = subscribeRepository.findByTopicNameAndTopicCategoryAndApiKey(name, category, apiKey);
        for (SubscribeEntity a : counts) {
            sum += a.getCount();
        }

        if (sum < 1) {
            cardDto.setAmount(BigDecimal.valueOf(0));
            subscribeEntity.setCount(1L);
            paymentClient.decreasePayment(cardDto);
        } else {
            cardDto.setAmount(amount);
            paymentClient.decreasePayment(cardDto);
            subscribeEntity.setCount(1L);
        }

        subscribeRepository.save(subscribeEntity);

        return SubscribeMap.INSTANCE.entityToDto(subscribeEntity);
    }

    public SubscribeDto createSubscribeWithBalance(String name, String category, String apiKey) {


        var subscribeEntity = get(name, category, apiKey);
        var amount = topicClient.getTopicAmount(name, category);

        int sum = 0;
        var counts = subscribeRepository.findByTopicNameAndTopicCategoryAndApiKey(name, category, apiKey);
        for (SubscribeEntity a : counts) {
            sum += a.getCount();
        }
        if (sum < 1) {
            subscribeEntity.setCount(1L);
            paymentClient.decreaseWithBalance(apiKey, BigDecimal.valueOf(0));
        } else {
            paymentClient.decreaseWithBalance(apiKey, amount);
            subscribeEntity.setCount(1L);
        }

        subscribeRepository.save(subscribeEntity);

        return SubscribeMap.INSTANCE.entityToDto(subscribeEntity);
    }


    private SubscribeEntity get(String name, String category, String apiKey) {
        var enity = subscribeRepository.findByTopicNameAndTopicCategoryAndApiKeyAndStatus(name, category, apiKey, Status.ACTIVE);


        if (enity != null) {
            throw new SubscribeException("TOPICS_ALREADY_EXSIST", String.format("%s topics and %s category already exist with %s apikey", name, category, apiKey));
        }


        return SubscribeEntity.builder()
                .apiKey(apiKey)
                .topicName(name)
                .topicCategory(category)
                .createdDate(LocalDateTime.now())
                .expireDate(LocalDateTime.now().plusSeconds(50))
                .status(Status.ACTIVE)
                .build();
    }


    public void schedulerStatusInactive() {

        var entity = subscribeRepository.findAll();
        for (SubscribeEntity en : entity) {
            if (en.getExpireDate().isBefore(LocalDateTime.now())) {
                log.info("Action.Log start schedulerStatus apiKey {} ",en.getApiKey());
                en.setStatus(Status.INACTIVE);
                subscribeRepository.save(en);
                log.info("Action.Log end schedulerStatus apiKey {} ",en.getApiKey());

            }
        }
        log.info("end");

    }



}







