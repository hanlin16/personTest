package com.callback2;

/**
 * @author liuxd
 * @version 1.0
 * @date 2020-04-02 10:57
 */
public class AsyncCallBack {

    public static void main(String[] args) {
        System.out.println("业务主线程开始ID：" + Thread.currentThread().getId());
        System.out.println("------------------");

        Son son = new Son();
        Mother mother = new Mother(son);

        mother.notice();
        son.writeHomeWork();

        System.out.println("业务主线程结束ID：" + Thread.currentThread().getId()+"\n");

    }

}

