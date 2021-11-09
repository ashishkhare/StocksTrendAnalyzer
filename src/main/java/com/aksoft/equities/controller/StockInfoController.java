package com.aksoft.equities.controller;

import com.aksoft.equities.entity.StockInfo;
import com.aksoft.equities.service.StockInfoService;
import com.aksoft.equities.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StockInfoController {
    @Autowired
    private StockInfoService stockInfoService;

    @PostMapping("/addStockInfo")
    public StockInfo addUser(@RequestBody StockInfo user) {
        return stockInfoService.saveStockInfo(user);
    }

    @PostMapping("/addStockInfoList")
    public List<StockInfo> addStockInfoList(@RequestBody List<StockInfo> users) {
        return stockInfoService.saveStockInfoList(users);
    }

    @GetMapping("/stockInfoList")
    public List<StockInfo> getStockInfoList() {
        return stockInfoService.getAllStockInfo();
    }

    @GetMapping("/stockinfoByIsin/{isin}")
    public List<StockInfo> findStockInfoByIsin(@PathVariable String isin) {
        return stockInfoService.getStockInfoByIsin(isin);
    }

    @GetMapping("/stockinfoBySymbol/{symbol}")
    public List<StockInfo> findStockInfoBySymbol(@PathVariable String symbol) {
        return stockInfoService.getStockInfoBySymbol(symbol);
    }

    @PostMapping("/upload/stocks")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        if (CSVHelper.isCSVFormat(file)) {
            try {
                stockInfoService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        } else {
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
}
