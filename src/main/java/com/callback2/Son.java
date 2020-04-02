package com.callback2;

/**
 * @author liuxd
 * @version 1.0
 * @date 2020-04-02 13:49
 */
public class Son {

    private String status = "";

    public void writeHomeWork() {
        System.out.println("小明写作业线程ID：" + Thread.currentThread().getId());
        System.err.println("小明写作业中...");
        setStatus("写作业中");
    }


    public void callback(String message) {
        System.out.println("回调小明吃饭线程ID：" + Thread.currentThread().getId());
        System.err.println(message);
        System.err.println("好的，马上来！");
        System.out.println("小明开始吃饭！");
        setStatus("吃饭中");
        System.out.println("小明执行吃饭线程ID：" + Thread.currentThread().getId());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
