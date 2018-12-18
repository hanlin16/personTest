package com.socket;

/**
 * Created by Liuxd on 2018-09-15.
 */
import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) {

        while (true) {
            System.out.println("控制台输入字符串,请录入：");
            Scanner input = new Scanner(System.in);
            String instr = input.nextLine();
            System.out.println("您刚才输入的是：" + instr);
            System.out.println("控制台输入字符串结束!!!");
            System.out.println("--------------------------------");
        }
    }

}