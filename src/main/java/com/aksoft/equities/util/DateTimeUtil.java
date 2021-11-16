package com.aksoft.equities.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    private static final String DATE_PATTERN = "dd/MM/yyyy";

    public static Date getFormattedDate(String dateStr) {
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
