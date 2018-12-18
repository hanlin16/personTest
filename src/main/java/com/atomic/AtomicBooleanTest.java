package com.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Liuxd on 2018-07-26.
 *
 * 能起到只有一个线程进入
 */
public class AtomicBooleanTest implements Runnable {
    private static int i = 0;
    private static AtomicBoolean bool = new AtomicBoolean(false);
    private String name;

    AtomicBooleanTest(String name) {
        this.name = name;
    }

    public void run() {
        Boolean flag = bool.compareAndSet(false, true);
        System.out.println(name + ":" + flag);
        if (flag) {
            ++i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("小明 是个:  " + name);
            System.out.println("小明 2222:  " + name);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("菜菜:  " + name);
            System.out.println("崔崔    :  " + name);

            System.out.println(i);
            bool.set(false);
        } else {
            System.out.println("线程失败" + name);
        }
    }

    public static void main(String[] args) {
        Thread thead1 = new Thread(new AtomicBooleanTest("逗笔"));
        thead1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread thead2 = new Thread(new AtomicBooleanTest("傻笔"));
        thead2.start();
    }
}
