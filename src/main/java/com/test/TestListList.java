package com.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestListList {

    public static void main(String[] args) {
        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        list1.add(1L);
        list1.add(3L);
        list1.add(5L);
        list1.add(7L);
        list1.add(9L);

        list2.add(1L);
        list2.add(3L);
        list2.add(6L);
        list2.add(8L);

        String list1Str = list1.toString();
        String list2Str = list2.toString();

        System.out.println(list1Str);
        System.out.println(list2Str);

        list1.removeAll(list2);
        list1Str = list1.toString();
        System.out.println(list1Str);

        list1.forEach(m ->{
            System.out.println(m);
            System.out.println(m);
        });



    }

}
