package com.atomic;

/**
 * Created by Liuxd on 2018-08-24.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {
    public static final AtomicInteger atomicInteger = new AtomicInteger(0);
    public static Integer num = new Integer(0);
    public static Integer numForHaveLock = new Integer(0);

    private static void testAtomicInteger() {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++)
            pool.execute(
                    new Thread(new Runnable() {
                        public void run() {
                            for (int j = 0; j < 2; j++) {
                                atomicInteger.incrementAndGet();
                                System.out.print(atomicInteger.get()+" ");
                            }
                        }
                    }) {
                    }
            );
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                System.out.println();
                System.out.println("atomicInteger自带锁最终结果是" + atomicInteger.get());
                break;
            }
        }

    }

    private static synchronized void integerAdd() {
        numForHaveLock++;
    }

    private static void testIntegerHaveLock() {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++)
            pool.execute(
                    new Thread(new Runnable() {
                        public void run() {
                            for (int j = 0; j < 2; j++) {
                                integerAdd();
                                System.out.print(numForHaveLock+" ");
                            }
                        }
                    }) {
                    }
            );
        pool.shutdown();

        while (true) {
            if (pool.isTerminated()) {
                System.out.println();
                System.out.println("有锁最终结果是" + numForHaveLock);
                break;
            }
        }

    }

    private static void testIntegerHaveNoLock() {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++)
            pool.execute(
                    new Thread(new Runnable() {
                        public void run() {
                            for (int j = 0; j < 2; j++) {
                                num++;
                                System.out.print(num+" ");
                            }
                        }
                    }) {
                    }
            );
        pool.shutdown();

        while (true) {
            if (pool.isTerminated()) {
                System.out.println();
                System.out.println("无锁最终结果是" + num);
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testAtomicInteger();
        testIntegerHaveLock();
        testIntegerHaveNoLock();

    }
}