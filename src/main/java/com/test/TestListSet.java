package com.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestListSet {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        Set<Long> set = new HashSet<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);

        set.add(1L);
        set.add(2L);
        set.add(3L);
        set.add(4L);

        String listStr = list.toString();
        String setStr = set.toString();

        System.out.println(listStr);
        System.out.println(setStr);
        System.out.println(listStr.equals(setStr));

    }

}
