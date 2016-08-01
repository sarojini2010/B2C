package com.example.thoughtchimp.dummyapplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by King on 04/02/16.
 */
public class DateUtil {

    public static final String YEAR_MONTH_DATE = "yyyy-MM-dd";
    public static final String MONTH_DAY_YEAR = "EEE, dd MMM yyyy";
    public static final String DAY_MONTH_TEAR = "dd MMM yyyy";
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SEC = "yyyy-MM-dd hh:mm:ss";

    public static String getFormattedDate(long unixTime, String format){
        Date date = new Date(unixTime);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static long getUnixTime(String dateStr, String dateFormat){
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            Date date = (Date)formatter.parse(dateStr);
            return date.getTime();
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getFormattedDate(int day, int month, int year, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getFormattedDate(String oldDateFormat, String newDateFormat, String dateStr) {
        Long unixTime = getUnixTime(dateStr, oldDateFormat);
        return getFormattedDate(unixTime, newDateFormat);
    }
}
