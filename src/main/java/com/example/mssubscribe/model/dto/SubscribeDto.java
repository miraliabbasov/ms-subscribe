package com.example.mssubscribe.model.dto;

import com.example.mssubscribe.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SubscribeDto {

    private String topicName;

    private String topicCategory;

    private LocalDateTime createdDate;

    private LocalDateTime expireDate;

    private Status status;

}
