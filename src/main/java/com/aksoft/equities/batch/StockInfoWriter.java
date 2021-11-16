package com.aksoft.equities.batch;

import com.aksoft.equities.entity.StockInfo;
import com.aksoft.equities.repository.StockInfoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockInfoWriter implements ItemWriter<StockInfo> {
    @Autowired
    private StockInfoRepository repository;

    @Override
    public void write(List<? extends StockInfo> list) throws Exception {
        repository.saveAll(list);
    }
}
