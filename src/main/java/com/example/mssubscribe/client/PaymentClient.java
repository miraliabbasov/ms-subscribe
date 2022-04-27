package com.example.mssubscribe.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "payment-client",
        url = "${client.user.url}",
        configuration = ClientDecoder.class)
public interface PaymentClient {



     @PostMapping("/card")
      void decreasePayment(@RequestBody PaymentCardDto dto);

     @PostMapping("/balance")
     BigDecimal decreaseWithBalance(@RequestHeader String apiKey , @RequestParam BigDecimal amount);

}
