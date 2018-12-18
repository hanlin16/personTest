package com.atomic;

/**
 * Created by Liuxd on 2018-07-26.
 *
 * 未能阻止其他线程进入执行
 */
public class Aomication implements Runnable {
    private static boolean exits = false;
    private String name;

    Aomication(String name) {
        this.name = name;
    }

    public void run() {
        if (!exits) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("小明 是个:  " + name);
            System.out.println("小明 2222:  " + name);
            exits = true;
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("菜菜:  " + name);
            System.out.println("崔崔    :  " + name);

            exits = true;
        } else {
            System.out.println("线程失败" + name);
        }
    }

    public static void main(String[] args) {
        Thread thead1 = new Thread(new Aomication("第一"));
        Thread thead2 = new Thread(new Aomication("第二"));

        thead1.start();
        thead2.start();
    }

}