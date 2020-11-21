package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TasksTest extends Thread {
    private static AtomicInteger atomic = new AtomicInteger(1);
    private int id;

    public TasksTest(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (atomic.get() <= 11) {
            while (atomic.get() % 3 == id) {
                System.out.println("thread_" + id + " id 执行结果:" + atomic.get());
                atomic.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread0 = new TasksTest(0);
        Thread thread1 = new TasksTest(1);
        Thread thread2 = new TasksTest(2);
        thread0.start();
        thread1.start();
        thread2.start();
    }
}