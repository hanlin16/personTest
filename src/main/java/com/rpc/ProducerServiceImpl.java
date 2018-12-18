package com.rpc;

/**
 * Created by Liuxd on 2018-09-14.
 */
public class ProducerServiceImpl implements ProducerService {

    public String produceCar(String modelName) {
        return "生产出汽车：" + modelName;
    }

}

