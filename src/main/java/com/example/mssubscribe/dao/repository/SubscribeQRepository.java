package com.example.mssubscribe.dao.repository;

import com.example.mssubscribe.dao.entity.QSubscribeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeQRepository extends JpaRepository<QSubscribeEntity, Long> {

}
