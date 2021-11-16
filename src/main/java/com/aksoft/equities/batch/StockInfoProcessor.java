package com.aksoft.equities.batch;

import com.aksoft.equities.entity.StockInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StockInfoProcessor implements ItemProcessor<StockInfo,StockInfo> {
    @Override
    public StockInfo process(StockInfo stockInfo) throws Exception {
        return stockInfo;
    }
}
