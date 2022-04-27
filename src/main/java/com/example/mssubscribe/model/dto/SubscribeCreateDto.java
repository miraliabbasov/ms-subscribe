package com.example.mssubscribe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeCreateDto {

    private String topicName;

    private String topicCategory;

}
