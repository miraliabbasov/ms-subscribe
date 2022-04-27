package com.example.mssubscribe.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "topic-client",
        url = "${client.topic.url}",
        configuration = TopicClientDecoder.class)
public interface TopicClient {

    @GetMapping("/topic-amount")
    BigDecimal getTopicAmount(@RequestParam String name,@RequestParam String category);
}
