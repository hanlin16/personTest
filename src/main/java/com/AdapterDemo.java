package com;

/**
 * Created by Liuxd on 2018-09-04.
 */
public class AdapterDemo {

    private static Integer calcute(Integer a, Integer b, Adapter adapter) {

        return adapter.execute(a, b);
    }

    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 2;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        Integer sum = calcute(a, b, new Adapter() {
            public Integer execute(Integer num1, Integer num2) {
                return num1 + num2;
            }
        });
        System.out.println("加和：" + sum);

        Integer minus = calcute(a, b, new Adapter() {
            public Integer execute(Integer num1, Integer num2) {
                return num1 - num2;
            }
        });
        System.out.println("差值：" + minus);

        Integer multiply = calcute(a, b, (num1, num2) -> num1 * num2);
        System.out.println("乘积：" + multiply);

        Integer division = calcute(a, b, (num1, num2) -> num1 / num2);
        System.out.println("商值：" + division);


    }

    private interface Adapter {
        Integer execute(Integer num1, Integer num2);
    }

}
