package com.example.mssubscribe.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCardDto {
    private String cardNumber;

    private BigDecimal amount;
}
