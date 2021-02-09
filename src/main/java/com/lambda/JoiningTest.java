package com.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JoiningTest {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(3);
        list.add(new Student("路飞", 22, 175));
        list.add(new Student("红发", 40, 180));
        list.add(new Student("白胡子", 50, 185));
        String names = list.stream().map(Student::getName).collect(Collectors.joining(",","[","]"));
        System.out.println(names);
    }

}
