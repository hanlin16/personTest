package com.lock;

/**
 * Created by Liuxd on 2018-11-11.
 */
public class BUSSB extends Thread {
    LockMethod lockMethod;

    void deal(LockMethod lockMethod) {
        this.lockMethod = lockMethod;
    }

    @Override
    public void run() {
        super.run();
        lockMethod.busiB();
    }
}
