package com.casLock;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-11-04 15:02
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class NoAtomicOperationDemo {
    static long count = 0;

    public static void add() {
        count++;
    }

    public static void main(String[] args) {
        NoAtomicOperationDemo.AtomicIntShow();
    }

    /**
     * 开启多个线程进行自加加操作
     * 赘述:多个线程必须共用一把锁*
     */
    public static void AtomicIntShow() {
        /**
         * jdk中ReentrantLock
         */
//       Lock lock = new ReentrantLock();
        /**
         * 使用 AtomicInteger 自实现的锁
         */
        Lock lock = new IConcurrentLock();
        System.out.println("启动...");
        ExecutorService threadpool = Executors.newFixedThreadPool(10);

        for (int k = 0; k < 100; k++) {
            threadpool.submit(new AddThread(lock));
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("result of acumulated sum=" + count);
        threadpool.shutdown();
        System.out.println("AtomicIntShow() exit");
        return;

    }

    /**
     * 执行调用的线程
     */
    public static class AddThread implements Runnable {
        Lock lock;

        public AddThread() {
        }

        public AddThread(Lock lock) {
            this.lock = lock;
        }

        public void run() {
            for (int k = 0; k < 1000; k++) {
                lock.lock();
                try {
                    NoAtomicOperationDemo.add();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }


}