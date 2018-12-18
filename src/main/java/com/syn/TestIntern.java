package com.syn;

/**
 * Created by Liuxd on 2018-08-10.
 */
public class TestIntern {

    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = str1 + str2;
        String str5 = "aaa" + "bbb";
        System.out.println(str3 == str4); // false
        System.out.println(str3 == str4.intern()); // true
        System.out.println(str3 == str5);// true

        int a =5;
        Long b =5L;
        System.out.println(a==b);

        Integer an = new Integer(9);
        int bn = 9;
        Integer cn = 9;

        System.out.println(an == bn);
        System.out.println(an == cn);
        System.out.println(bn == cn);

        System.out.println(an > 8);
    }
}
