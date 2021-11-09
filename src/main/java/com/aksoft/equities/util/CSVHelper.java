package com.aksoft.equities.util;

import com.aksoft.equities.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    private static final String TYPE = "text/csv";
    private static final String[] HEADER_COLUMNS = {"FirstName", "LastName", "Address", "City", "EmailId", "Cellphone"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return (TYPE.equals(file.getContentType()));
    }

    public static List<User> csvToUsers(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            List<User> users = new ArrayList<>();
            for (CSVRecord csvRecord : csvRecords) {
                User user = new User();
                user.setFirstName(csvRecord.get(HEADER_COLUMNS[0]));
                user.setLastName(csvRecord.get(HEADER_COLUMNS[1]));
                user.setAddress(csvRecord.get(HEADER_COLUMNS[2]));
                user.setCity(csvRecord.get(HEADER_COLUMNS[3]));
                user.setEmailId(csvRecord.get(HEADER_COLUMNS[4]));
                user.setCellPhone(csvRecord.get(HEADER_COLUMNS[5]));
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}
