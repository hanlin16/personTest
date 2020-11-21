package com.iretry;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

public class IRetryTest {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(10);
        //配置最大线程数
        executor.setMaxPoolSize(20);
        //配置队列大小
        executor.setQueueCapacity(1000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-system-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();

        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("Scheduler-");
        scheduler.setPoolSize(5);
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        scheduler.initialize();

        RetryUtils retryUtils = new RetryUtils(executor, scheduler);
        IRetryTest iRetryTest = new IRetryTest();
        iRetryTest.executeBusiness(retryUtils, new UserInfo("Jack"));


    }

    private void executeBusiness(RetryUtils retryUtils, UserInfo user) {

        retryUtils.doRetry(new int[]{6, 12}, new Task() {
            @Override
            public void run() throws Exception {
                user.setName("Henry");
                System.out.println("执行业务,给员工修改名称为：" + user.getName());
                Integer a = 1 / 0;

            }

            //最终失败后，回滚
            @Override
            public void retryFailed(Throwable e) {
                user.setName("Jack");
                System.out.println("重试失败，业务数据回滚...");
                System.out.println("用户名称为：" + user.getName());
            }

            @Override
            public Object snapshot() {
                return user;
            }
        });
    }
}
