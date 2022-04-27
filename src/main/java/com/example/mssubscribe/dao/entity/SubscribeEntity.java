package com.example.mssubscribe.dao.entity;

import com.example.mssubscribe.model.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subscribe")
public class SubscribeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "topics_name")
    private String topicName;

    @Column(name = "topics_category")
    private String topicCategory;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long count;

}
