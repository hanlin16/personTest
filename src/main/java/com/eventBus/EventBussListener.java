package com.eventBus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Liuxd on 2018-08-19.
 */
public class EventBussListener {

    public final static Factory factory = new Factory();

    @Subscribe
    @AllowConcurrentEvents
    public void executeOperate(Car car) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        factory.operate(car);
//        throw new RuntimeException("认为抛出异常");
    }

}