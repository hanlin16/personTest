package com.getClass;

import com.alibaba.fastjson.JSON;
import com.getClass.vo2.Z;

import java.util.Arrays;

public class TestArrayToString {

    public static void main(String[] args) {
        Z[] zz = new Z[3];
        zz[0] = new Z();
        zz[1] = new Z();
        zz[2] = new Z();

        String s1 = Arrays.toString(zz);
        String s2 = JSON.toJSONString(zz);

        System.out.println(s1);
        System.out.println(s2);
    }
}
