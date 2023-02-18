package com.example.fidelitycardsapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static final SimpleDateFormat formatter = new SimpleDateFormat(String.valueOf(R.string.DatePattern), Locale.US);

    public static Date fromString(String value){
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String fromDate(Date value){
        if(value==null){
            return null;
        }
        return formatter.format(value);
    }
}
