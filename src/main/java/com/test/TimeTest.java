package com.test;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeTest {

    public static void main(String[] args) {
        List<Date> list = getDateList(1, DateUtils.addMinutes(new Date(), -0 * 10), new Date());

        for (int i = 0; i < 24; i++) {
            System.out.println(format(list.get(i), "yyyy-MM-dd HH:mm:ss"));
        }

    }


    public static List<Date> getDateList(int size, Date from, Date to) {
        List<Date> list = new ArrayList<>();
        long minutes = getMinutes(from, to);
        long interval = (long) Math.ceil(((double) (minutes)) / ((double) size - 1));
        list.add(from);

        if (size > 24) {
            interval = minutes / (24 - 2);
        }

        if (interval == 0) {
            interval = 10;
        }

        for (int i = 0; i < 23; i++) {
            list.add(DateUtils.addMinutes(from, (int) interval * (i + 1)));
        }

        return list;
    }


    public static Long getMinutes(Date fromDate, Date toDate) {
        Long minutes = (toDate.getTime() - fromDate.getTime()) / (1000 * 60);
        return minutes;
    }


    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

}
