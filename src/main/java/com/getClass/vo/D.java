package com.getClass.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class D {

    private List<E> list = new ArrayList<E>(Arrays.asList(new E("张三"), new E("李四"), new E("王五")));


}
