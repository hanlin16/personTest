package com.callBack;

/**
 * @author liuxd
 * @version 1.0
 * @date 2020-04-02 8:48
 */
public class CallBckTest {

    public static void main(String[] args) {
        MainBusiness mainBusiness = new MainBusiness();

        System.out.println("主线程："+Thread.currentThread().getId());
        System.out.println("*********具体实现类实现的回调方法_固定*********");
        mainBusiness.execute(new CallbackServiceImpl());

        System.out.println("*********匿名内部类实现的回调方法_灵活*********");
        mainBusiness.execute(new CallbackService() {
            public void callBackFunc() {
                System.out.println("匿名内部类回到函数线程："+Thread.currentThread().getId());
                System.out.println("匿名内部类回调函数开始执行...");
                System.out.println("匿名内部类回调函数结束执行...\n");
            }
        });
    }
}
