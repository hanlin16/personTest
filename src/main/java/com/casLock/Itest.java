package com.casLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-11-04 15:21
 */
public class Itest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        boolean flag = atomicInteger.compareAndSet(0, 1);
        System.out.println(flag);
    }
}
