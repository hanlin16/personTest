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

        RetryHelper retryHelper = new RetryHelper(executor, scheduler);
        IRetryTest iRetryTest = new IRetryTest();
        iRetryTest.executeBusiness(retryHelper, new InfoVO("Jack"));


    }

    private void executeBusiness(RetryHelper retryHelper, InfoVO vo) {

        retryHelper.doRetry(new int[]{60, 180}, new RetryTask() {
            @Override
            public void run() throws Exception {
                System.out.println("执行业务" + vo.getName());
                Integer a = 1 / 0;

            }

            //最终失败后，本地租户并发回滚
            @Override
            public void retryFailed(Throwable e) {
                System.out.println("重试调度失败，租户并发回滚...");
            }

            @Override
            public Object snapshot() {
                return vo;
            }
        });
    }
}
