package com.yts.tsletter.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    public final static String DATE_FORMAT = "yyyy-MM-dd (E)";

    public final static String FULL_FORMAT = "yyyy-MM-dd (E) a hh:mm";

    public final static String WRITE_RECEIVE_FORMAT = "yyyy  MM.dd E";


    public final static String DAY_FORMAT = "d";

    public static String getDate(long date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date d = new Date(date);
        return formatter.format(d).toUpperCase();
    }

    public static Calendar getCalendar(long date) {
        if (date == 0) {
            date = System.currentTimeMillis();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar;

    }

    public static int getWeek(long date) {
        return getCalendar(date).get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isBlindView(long date) {
        return System.currentTimeMillis() <= date;
    }

    public static boolean isCompareDay(long one, long two) {
        return getDate(one, DAY_FORMAT).equals(getDate(two, DAY_FORMAT));
    }
}
