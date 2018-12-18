package com.syn;

/**
 * Created by Liuxd on 2018-12-12.
 */
public class VolatileCounting {
    private static volatile int count = 0;

    private static void addCount() {
        count++;
        System.out.println(count);
    }

    public static void main(String[] args) {
        int threadCount = 1000;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        addCount();
                    }
                }
            });
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
    }
}
