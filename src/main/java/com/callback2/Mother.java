package com.callback2;

/**
 * @author liuxd
 * @version 1.0
 * @date 2020-04-02 13:49
 */
public class Mother {

    private Son son;

    public Mother(Son son) {
        this.son = son;
    }

    public void notice() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通知母亲线程ID：" + Thread.currentThread().getId());
                cookFood("面包");
            }
        }).start();
    }

    public void cookFood(String bread) {
        System.out.println("目前做饭线程ID：" + Thread.currentThread().getId());
        try {
            System.out.println("母亲烤制" + bread + "中...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("母亲烤好了面包");
        String message = "小明，来吃饭了！";

        son.callback(message);

    }

}
