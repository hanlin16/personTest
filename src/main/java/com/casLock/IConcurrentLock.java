package com.casLock;

/**
 * Java 借用AtomicInteger实现同步锁
 * 类比ReentrantLock
 *
 * @author liuxd
 * @version 1.0
 * @date 2019-11-04 15:00
 */

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class IConcurrentLock implements Lock {
    //初始化为 0
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private ConcurrentLinkedQueue<Thread> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    /**
     * 上锁
     */
    @Override
    public void lock() {
        if (atomicInteger.compareAndSet(0, 1)) {
            return;
        }

        //首先，将当前线程放置等待队列中
        concurrentLinkedQueue.add(Thread.currentThread());

        //自旋_死等
        while (true) {
            if (0 == atomicInteger.get()) {
                //期望是0，是0返回true，否则加1并返回false
                if (atomicInteger.compareAndSet(0, 1)) {
                    concurrentLinkedQueue.remove(Thread.currentThread());
                    return;
                }
            } else {
                //挂起当前线程
                LockSupport.park();
            }
        }
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        atomicInteger.set(0);
        //唤醒等待队列的第一个线程
        Thread waiterHead = concurrentLinkedQueue.peek();
        if (null != waiterHead) {
            //唤醒线程
            LockSupport.unpark(waiterHead);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}