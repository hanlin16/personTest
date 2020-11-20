package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestCopy {

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("张三", 10),
                new Person("李四", 12)
        );

        List<String> nameList = persons.stream().map(person -> person.getName()).collect(Collectors.toList());

        System.out.println("nameList: = " + nameList);

        List<Student> students = persons.stream().map(person -> {
            return new Student(person.getName(), person.getAge());
        }).collect(Collectors.toList());


        System.out.println("students := " + students);

        String date ="20:00:00";

        String dateStr =date.substring(0,5);
        System.out.println(dateStr);


    }
}
