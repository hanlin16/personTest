package com.stream;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;
    private Integer age;

    public Student(String s, int i, int i1) {
    }
}
