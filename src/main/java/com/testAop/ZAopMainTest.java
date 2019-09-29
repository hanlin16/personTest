package com.testAop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ZAopMainTest {

    public static void main(String[] args) {
        // 通过Java配置来实例化Spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        // 在Spring容器中获取Bean对象
        LogPrinter logPrinter = context.getBean(LogPrinter.class);
        logPrinter.printLog();


        // 销毁该容器
        context.destroy();
    }
}
