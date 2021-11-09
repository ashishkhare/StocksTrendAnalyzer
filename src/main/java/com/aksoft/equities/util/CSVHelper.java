package com.aksoft.equities.util;

import com.aksoft.equities.entity.StockInfo;
import com.aksoft.equities.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVHelper {
    private static final String TYPE = "text/csv";
    private static final String[] USER_HEADER_COLUMNS = {"FirstName", "LastName", "Address", "City", "EmailId", "Cellphone"};
    private static final String[] STOCK_INFO_HEADER_COLUMNS = {"Date", "Open", "High", "Low", "Close", "Adj Close", "Volume", "Name", "ISIN"};
    private static final String DECIMAL_PATTERN = "#,##0.0#";
    private static final String DATE_PATTERN = "dd/MM/yyyy";

    public static boolean isCSVFormat(MultipartFile file) {
        return (TYPE.equals(file.getContentType()));
    }

    public static List<User> csvToUsers(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            List<User> users = new ArrayList<>();
            for (CSVRecord csvRecord : csvRecords) {
                User user = new User();
                user.setFirstName(csvRecord.get(USER_HEADER_COLUMNS[0]));
                user.setLastName(csvRecord.get(USER_HEADER_COLUMNS[1]));
                user.setAddress(csvRecord.get(USER_HEADER_COLUMNS[2]));
                user.setCity(csvRecord.get(USER_HEADER_COLUMNS[3]));
                user.setEmailId(csvRecord.get(USER_HEADER_COLUMNS[4]));
                user.setCellPhone(csvRecord.get(USER_HEADER_COLUMNS[5]));
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public static List<StockInfo> csvToStockInfoList(InputStream is) {
        List<StockInfo> list = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            DecimalFormat decimalFormat = getDecimalFormatter();
            for (CSVRecord csvRecord : csvRecords) {
                StockInfo stockInfo = new StockInfo();
                stockInfo.setSymbol(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[7]));
                stockInfo.setIsin(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[8]));
                stockInfo.setOpenPrice((BigDecimal) decimalFormat.parse(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[1])));
                stockInfo.setHighPrice((BigDecimal) decimalFormat.parse(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[2])));
                stockInfo.setLowPrice((BigDecimal) decimalFormat.parse(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[3])));
                stockInfo.setClosePrice((BigDecimal) decimalFormat.parse(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[4])));
                stockInfo.setAdjClosePrice((BigDecimal) decimalFormat.parse(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[5])));
                stockInfo.setVolume(BigInteger.valueOf(Long.valueOf(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[6]))));
                stockInfo.setLastTradeDate(getFromattedDate(csvRecord.get(STOCK_INFO_HEADER_COLUMNS[0])));
                list.add(stockInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static DecimalFormat getDecimalFormatter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_PATTERN, symbols);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }

    private static Date getFromattedDate(String dateStr) {
        DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        Date date = null;
        try {
            if (!dateStr.isEmpty())
                date = format.parse(dateStr);
            else
                date = format.parse(format.format(new Date()));
        } catch (Exception ex) {
        }
        return date;
    }
}
