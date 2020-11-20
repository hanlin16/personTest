package com.test;

/**
 * Created by Liuxd on 2018-08-09.
 */
public class Test {

    public static void main(String[] args) {
        String str = "abc_234";
        str = str.substring(0,str.indexOf("_"));
        System.out.println(str);
    }


}


