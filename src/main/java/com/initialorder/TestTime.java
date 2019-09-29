package com.initialorder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {

    public static void main(String[] args) {
        String dateStr = fromLongToDate("yyyy-MM-dd HH:mm:ss", 1568020783663L);
        System.out.println(dateStr);
    }

    /**
     * Long类型时间->转换成日期->转成要求格式的String类型
     */
    public static String fromLongToDate(String format, Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(time);
        return sdf.format(date);
    }


}
