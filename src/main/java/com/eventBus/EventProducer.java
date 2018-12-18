package com.eventBus;

import com.google.common.eventbus.EventBus;

import java.util.Random;

/**
 * Created by Liuxd on 2018-08-19.
 */
public class EventProducer {

    public final static String[] nameArray = new String[]{"奥迪", "大众", "奔驰", "宝马", "尼桑"};
    public final static String[] colorArray = new String[]{"黑色", "白色", "红色", "银色", "黄色"};

    public final static Random random = new Random();

    private final static EventBus eventBus = new EventBus();

    private final static EventBussListener loggerListener = new EventBussListener();

    public static void main(String[] args) {
        eventBus.register(loggerListener);

        System.out.println("主线程ID:" + Thread.currentThread().getId());
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                @Override
                public void run() {
                    Car car = new Car(nameArray[random.nextInt(5)], colorArray[random.nextInt(5)]);
                    eventBus.post(car);
                }
            }.start();
        }
    }
}
