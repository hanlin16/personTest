package com.syn;

/**
 * Created by Liuxd on 2018-08-10.
 */
public class TestSyn extends Thread {

    private TestPrint testPrint;
    private String str;

    public TestSyn(TestPrint testPrint, String str) {
        this.testPrint = testPrint;
        this.str = str;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getId() + "开始执行...");
            testPrint.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        TestPrint testPrint = new TestPrint();
        for (int i = 1; i < 100; i++) {
//          String str = "锁定订单ID"+i;
            String str = "锁定订单ID";//替换本行与上一行，执行时间会有区别
            TestSyn testSyn = new TestSyn(testPrint, String.valueOf(str));
            testSyn.start();
        }


    }


}
