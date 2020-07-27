package com.num;

import java.text.DecimalFormat;

public class DoubleTest {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.##");

        Long a = 5000L;
        Long b = 10000L;
        Double a1 = (double) a;
        Double b1 = (double) b;
        Double c = a1 / b1;
        System.out.println(a1 / b1);
        System.out.println(c);

        Double get_double = Double.parseDouble(df.format(c));
        System.out.println(get_double);
        get_double = Double.parseDouble(String.format("%.2f",a1 / b1));
        System.out.println(get_double);

        c = 1000D;

        get_double = Double.parseDouble(df.format(c));
        System.out.println(get_double);

        get_double = Double.parseDouble(String.format("%.2f",c));
        System.out.println(get_double);
    }
}
