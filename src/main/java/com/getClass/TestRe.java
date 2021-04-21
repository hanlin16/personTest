package com.getClass;

public class TestRe {

    public static void main(String[] args) {
        String str = "as[0].ito.[].name";
        int begin = str.indexOf("[") + 1;
        int end = str.indexOf("]");
        String s = str.substring(begin, end);
        System.out.println(s);


        int num = str.lastIndexOf(".") + 1;
        String filedName = str.substring(num);
        System.out.println(filedName);

        int begin1 = str.indexOf("]")+2;

        int end1 = str.lastIndexOf(".") ;

        String middle = str.substring(begin1,end1);
        System.out.println(middle);

        String[] array = middle.split(".");
        for(String ss:array) {
            System.out.println(ss);
        }
    }

}
