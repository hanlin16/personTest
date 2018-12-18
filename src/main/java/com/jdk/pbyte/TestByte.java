package com.jdk.pbyte;

/**
 * Created by Liuxd on 2018-11-03.
 */
public class TestByte {
    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        int number = 10;
        //原始数二进制
        printNumBinaryInfo("原始数据",number);
        System.out.println("-----------------------------------");

        //左移一位
        int numberLeft1 = number << 1;
        printNumBinaryInfo("左移一位", numberLeft1);

        //右移一位
        int numberRight1 = number >> 1;
        printNumBinaryInfo("右移一位", numberRight1);

        //左移二位
        int numberLeft2 = number << 2;
        printNumBinaryInfo("左移二位", numberLeft2);

        //右移二位
        int numberRight2 = number >> 2;
        printNumBinaryInfo("右移二位", numberRight2);

        //无符号右移一位
        int numberNoSymBolRight1 = number >>> 1;
        printNumBinaryInfo("无符号右移一位", numberNoSymBolRight1);

        //无符号右移一位
        int numberNoSymBolRight2 = number >>> 2;
        printNumBinaryInfo("无符号右移二位", numberNoSymBolRight2);

        System.out.println("-----------------------------------");

    }

    /**
     * 打印信息
     * @param msg
     * @param num
     */
    private static void printNumBinaryInfo(String msg, int num) {
        System.out.println(msg + " 十进制：" + num + " 对应二进制:" + Integer.toBinaryString(num));
    }
}