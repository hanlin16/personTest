package com.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Liuxd on 2018-07-18.
 */
public class TestSemaphone implements Runnable {

    private final Semaphore semaphore = new Semaphore(5);

    public void run() {
        try {
            /**
             * 尝试获得一个许可，不断尝试，直到获得许可
             */
            semaphore.acquire();

            /**
             * 模拟耗时操作
             */
            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName() + " done!");

            /**
             * 释放许可
             */
            semaphore.release();


        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

        }

    }

    public static void main(String[] argv) throws InterruptedException {

        TestSemaphone r = new TestSemaphone();

        ExecutorService exec = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {

            exec.submit(r);

        }

        exec.shutdown();

    }

}


