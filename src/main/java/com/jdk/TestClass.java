package com.jdk;

/**
 * Created by Liuxd on 2018-11-02.
 */
public class TestClass {
    public static void main(String[] args) {
        new Child("param");
    }
}

class Parent {
    static {
        System.out.println("执行父类静态代码块");
    }

    {
        System.out.println("执行父类构造代码块");
    }

    Parent() {
        System.out.println("执行父类无参数构造方法");
    }

    Parent(String str) {
        System.out.println("执行父类带参数构造方法，参数：" + str);
    }

}

class Child extends Parent {
    static {
        System.out.println("执行子类静态代码块");
    }

    {
        System.out.println("执行子类构造代码块");
    }

    Child() {
        System.out.println("执行子类无参数构造方法");
    }

    Child(String str) {
        super(str);
        System.out.println("执行子类带参数构造方法，参数" + str);
    }

}
