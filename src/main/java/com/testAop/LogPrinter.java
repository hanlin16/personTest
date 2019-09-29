package com.testAop;


import org.springframework.stereotype.Component;

// 代理目标类
@Component
public class LogPrinter implements Printable {
    @Override
    public void printLog() {
        System.out.println("打印Log...");
    }
}