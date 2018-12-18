package com.single;

/**
 * Created by Liuxd on 2018-11-09.
 */
public class TestSignle implements Runnable {

    public static LazySingleton1 lazySingleton1 = null;
    public static LazySingleton1 lazySingleton2 = null;
    @Override
    public void run() {
        LazySingleton1 lazySingleton1 = null;
    }
}
