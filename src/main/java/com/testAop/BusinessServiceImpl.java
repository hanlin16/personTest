package com.testAop;


import org.springframework.stereotype.Component;

// 代理目标类
@Component
public class BusinessServiceImpl implements BusinessService {
    @Override
    public void execute() {
        System.out.println("执行业务程序...");
    }
}