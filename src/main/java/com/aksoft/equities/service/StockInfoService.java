package com.aksoft.equities.service;

import com.aksoft.equities.entity.StockInfo;
import com.aksoft.equities.repository.StockInfoRepository;
import com.aksoft.equities.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StockInfoService {
    @Autowired
    private StockInfoRepository repository;

    public StockInfo saveStockInfo(StockInfo info){
        return repository.save(info);
    }
    public List<StockInfo> saveStockInfoList(List<StockInfo> list){
        return repository.saveAll(list);
    }
    public List<StockInfo> getAllStockInfo(){
        return repository.findAll();
    }
    public List<StockInfo> getStockInfoBySymbol(String symbol){
        return repository.findBySymbol(symbol);
    }
    public List<StockInfo> getStockInfoByIsin(String isin){
        return repository.findByIsin(isin);
    }

    public void save(MultipartFile file) {
        try {
            List<StockInfo> stockInfoList = CSVHelper.csvToStockInfoList(file.getInputStream());
            saveStockInfoList(stockInfoList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store csv data: " + e.getMessage());
        }
    }
}
