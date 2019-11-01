package com.classLoad;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-10-31 16:42
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = 1;
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
