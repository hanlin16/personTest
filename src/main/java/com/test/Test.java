package com.test;

/**
 * Created by Liuxd on 2018-08-09.
 */
public class Test {

    public static void main(String[] args) {
       /* String str ="20180524000009";
        System.out.println(str);
        System.out.println(str.substring(8,str.length()));
        System.out.println(str.substring(9,str.length()));

        System.out.println(new Random(1).nextInt());

        Long l = null;
        System.out.println(null == l);

        String ss ="2018-08-11 15:21:23";
        String result = ss.substring(5,10);
        System.out.println(result);*/

        String str2 = "20180109001";
        System.out.println(str2.substring(8,str2.length()));
        String str3 = "2018-01-09001";

        System.out.println(str3.substring(0,7));

        System.out.println(String.valueOf((int) Double.parseDouble("2.00")));

        int a =1;
        Integer b =1;
        String str ="abc";
        cal(a,b,str);

        System.out.println(a);
        System.out.println(b);
        System.out.println(str);

    }

    public static void cal(int a, Integer b,String str) {
        a = a + 1;
        b = b + 1;
        str="prefix_"+str;

    }
}
