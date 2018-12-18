package com.eventBus;

/**
 * Created by Liuxd on 2018-08-19.
 */
public class Factory {

    public static Integer num = 0;

    public void operate(Car car) {
        System.out.println("当前累计num:" + num++);
        System.out.println("生产新汽车,名称" + car.getName() + "颜色" + car.getColor() + " 线程ID：" + Thread.currentThread().getId());

    }


}
