package com.model.single;

public class Singleton {

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
        System.out.println("初始化单例..");
    }

    public static final Singleton getInstance() {
        System.out.println("获取单例");
        return SingletonHolder.INSTANCE;
    }
}