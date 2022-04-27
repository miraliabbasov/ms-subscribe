package com.example.mssubscribe.dao.repository;

import com.example.mssubscribe.dao.entity.SubscribeEntity;
import com.example.mssubscribe.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity,Long> {

    SubscribeEntity findByTopicNameAndTopicCategoryAndApiKeyAndStatus (String name, String category , String apiKey, Status status);

    List<SubscribeEntity> findByTopicNameAndTopicCategoryAndApiKey(String name, String category, String apiKey);

}
