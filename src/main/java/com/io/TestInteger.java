package com.io;

import java.math.BigInteger;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-11-01 16:30
 */
public class TestInteger {

    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("1");
        while(true) {
            bigInteger = bigInteger.add(new BigInteger("1"));
            System.out.println(bigInteger.toString());
        }
    }
}
