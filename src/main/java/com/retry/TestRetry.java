package com.retry;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-11-06 17:36
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestRetry {
    //最大重试次数
    private static final Integer tryTimes = 6;
    //重试间隔时间单位秒
    private static final Integer intervalTime = 2;

    public static void main(String[] args) throws InterruptedException {
        boolean flag = TestRetry.retryBuss();
        System.out.println("最终执行结果:" + (flag ? "成功" : "失败"));
    }

    public static boolean retryBuss() throws InterruptedException {
        Integer retryNum = 1;
        boolean flag = false;
        while (retryNum <= tryTimes) {
            try {
                flag = execute(retryNum);
                if (flag) {
                    System.out.println("第" + retryNum + "次执行成功!!!");
                    break;
                }
                System.err.println("第" + retryNum + "次执行失败...");
                retryNum++;
            } catch (Exception e) {
                retryNum++;
                TimeUnit.SECONDS.sleep(intervalTime);
                continue;
            }
        }

        return flag;
    }

    /**
     * 具体业务
     * @param retryNum
     * @return
     */
    private static boolean execute(int retryNum) {
        Random random = new Random();
        int a = random.nextInt(10);
        boolean flag = true;
        try {
            if (a != 6) {
                flag = false;
                throw new RuntimeException();
            }
        } catch (Exception e) {
        }
        return flag;
    }
}