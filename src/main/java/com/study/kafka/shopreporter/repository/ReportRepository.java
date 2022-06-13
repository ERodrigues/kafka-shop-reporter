package com.study.kafka.shopreporter.repository;

import com.study.kafka.shopreporter.model.ShopReporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopReporter, Long> {
    @Modifying
    @Query(value = "update shop_report set amount = amount+1 where identifier = :shopStatus", nativeQuery = true)
    void incrementShopStatus(String shopStatus);
}