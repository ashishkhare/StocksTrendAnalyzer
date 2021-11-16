package com.aksoft.equities.repository;

import com.aksoft.equities.entity.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockInfoRepository extends JpaRepository<StockInfo,Integer> {
    List<StockInfo> findByName(String symbol);
    List<StockInfo> findByIsin(String isin);
}
