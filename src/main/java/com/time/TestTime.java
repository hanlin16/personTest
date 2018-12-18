package com.time;


import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Liuxd on 2018-08-28.
 */
public class TestTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final FastDateFormat timeFormat = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void TestDateTimeFormatter(ExecutorService pool) {
        for (int i = 0; i < 100000; i++) {
            Date date = DateUtils.addDays(new Date(), i);
            pool.execute(new Thread() {
                @Override
                public void run() {
                    String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(dateStr, formatter);
                    String dateAfter = formatter.format(localDate);
                    System.out.println(dateStr + "   " + dateAfter);
                    if (!dateStr.equals(formatter.format(localDate))) {
                        System.out.println(dateStr + "   " + dateAfter + "   发生异常情况");
                    }
                }
            });

        }
    }

    public static void TestFastDateFormat(ExecutorService pool) {
        for (int i = 0; i < 100000; i++) {
            Date date = DateUtils.addDays(new Date(), i);
            pool.execute(new Thread() {
                @Override
                public void run() {
                    String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");

                    String dateAfter = timeFormat.format(date);

                    System.out.println(dateStr + "   " + dateAfter);

                    if (!dateStr.equals(dateAfter)) {
                        System.out.println(dateStr + "   " + dateAfter + "   发生异常情况");
                    }
                }
            });

        }
    }

    /**
     * 会发生异常情况的日期转换
     * @param pool
     */
    public static void TestSimpleDateFormat(ExecutorService pool) {
        for (int i = 0; i < 100000; i++) {
            Date date = DateUtils.addDays(new Date(), i);

            pool.execute(new Thread() {
                @Override
                public void run() {
                    String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");

                    /**
                     * SimpleDateFormat 对象，每次使用都使用新实例
                     */
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateAfter = simpleDateFormat.format(date);

                    System.out.println(dateStr + "   " + dateAfter);

                    if (!dateStr.equals(dateAfter)) {
                        System.out.println(dateStr + "   " + dateAfter + "   发生异常情况");
                    }
                }
            });

        }
    }

    public static void TestDateFormatUtils(ExecutorService pool) {
        for (int i = 0; i < 100000; i++) {
            Date date = DateUtils.addDays(new Date(), i);

            pool.execute(new Thread() {
                @Override
                public void run() {
                    String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");

                    String dateAfter = DateFormatUtils.format(date, "yyyy-MM-dd");

                    System.out.println(dateStr + "   " + dateAfter);

                    if (!dateStr.equals(dateAfter)) {
                        System.out.println(dateStr + "   " + dateAfter + "   发生异常情况");
                    }
                }
            });

        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1000);
//        TestDateTimeFormatter(pool);
//        TestFastDateFormat(pool);
        TestSimpleDateFormat(pool);
//        TestDateFormatUtils(pool);

    }


}
