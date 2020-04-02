package com.callBack;

/**
 * @author liuxd
 * @version 1.0
 * @date 2020-04-02 8:47
 */
public class CallbackServiceImpl implements CallbackService {
    @Override
    public void callBackFunc() {
        System.out.println("具体实现类回到函数线程："+Thread.currentThread().getId());
        System.out.println("具体实现类回调函数开始执行...");
        System.out.println("具体实现类回调函数结束执行...\n");
    }
}
