package com.test;

import org.apache.commons.lang.time.DateFormatUtils;

public class TestTimes {

    public static void main(String[] args) {
        String time = DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(time);

        String str ="abc";
        String ss = str.substring(0,str.length()-1);
        System.out.println(ss);
    }
}
