package com.lock;

/**
 * Created by Liuxd on 2018-11-11.
 */
public class TestLockMethod extends Thread {

    public static void main(String[] args) {
        LockMethod lockMethod = new LockMethod();
        BUSSA bussa = new BUSSA();
        BUSSB bussb = new BUSSB();
        bussa.deal(lockMethod);
        bussb.deal(lockMethod);
        bussa.start();
        bussb.start();

    }
}
