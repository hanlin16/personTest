package com.jdk;

/**
 * Created by Liuxd on 2018-11-02.
 */
public class TestStatic {

    public static final int num = 0;

    public TestStatic() {
        System.out.println("构造函数...");
    }

    static {
        System.out.println("静态代码块...");
    }


    public static void main(String[] args) {
        TestStatic testStatic = new TestStatic();
    }

}
