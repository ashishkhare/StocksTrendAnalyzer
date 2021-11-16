package com.aksoft.equities.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class NumberUtil {
    private static final String DECIMAL_PATTERN = "#,##0.0#";

    public static DecimalFormat getDecimalFormatter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_PATTERN, symbols);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }
}
