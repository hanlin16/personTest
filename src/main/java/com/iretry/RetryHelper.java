package com.iretry;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/**
 * 重试工具类
 * //TODO 持久化
 * @author Liuxd
 * @date 2020-06-14
 */
@Slf4j
public class RetryHelper {

    private final static int DEFAULT_RETRY_TIME = 6;

    private final static int[] DEFAULT_DELAY_SECONDS = {3, 30, 180, 600, 1800, 3600};

    private static Queue<RetryRunnable> TASK_QUEUE;
    private final Executor executor;

    public RetryHelper(ThreadPoolTaskExecutor executor, ThreadPoolTaskScheduler scheduler) {
        TASK_QUEUE = new ConcurrentLinkedQueue<>();
        this.executor = executor;

        //每秒执行，遍历任务集合，如果需要执行时，线程池调度执行
        scheduler.scheduleAtFixedRate(()->{
            for (RetryRunnable task : TASK_QUEUE) {
                long nextRetryMillis = task.nextRetryMillis;
                if (nextRetryMillis != -1 && nextRetryMillis <= System.currentTimeMillis()) {
                    task.nextRetryMillis = -1;
                    executor.execute(task);
                }
            }
        }, 1000);
    }

    public  void doRetry(RetryTask retryTask){
        doRetry(DEFAULT_RETRY_TIME, DEFAULT_DELAY_SECONDS, retryTask);
    }

    public  void doRetry(int maxRetryTime, RetryTask retryTask){
        doRetry(maxRetryTime, DEFAULT_DELAY_SECONDS, retryTask);
    }

    public  void doRetry(int[] retryDelaySeconds, RetryTask retryTask){
        doRetry(retryDelaySeconds.length, retryDelaySeconds, retryTask);
    }

    /**
     * @param maxRetryTime      最大重试次数
     * @param retryDelaySeconds 每次重试间隔时间数组
     * @param retryTask         需要重试的任务
     */
    public  void doRetry(final int maxRetryTime,final int[] retryDelaySeconds,final RetryTask retryTask){
        Runnable runnable = new RetryRunnable(maxRetryTime, retryDelaySeconds, retryTask);
        executor.execute(runnable);
    }

    private static class RetryRunnable implements Runnable {

        private final RetryTask retryTask;
        private final int maxRetryTime;
        private final int[] retryDelaySeconds;

        private int retryTime;
        private volatile long nextRetryMillis;

        public RetryRunnable(final int maxRetryTime, final int[] retryDelaySeconds, final RetryTask retryTask) {
            this.retryTask = retryTask;
            if (maxRetryTime <= 0) {
                this.maxRetryTime = DEFAULT_RETRY_TIME;
            }else {
                this.maxRetryTime = maxRetryTime;
            }
            if (retryDelaySeconds == null || retryDelaySeconds.length == 0) {
                this.retryDelaySeconds = DEFAULT_DELAY_SECONDS;
            }else {
                this.retryDelaySeconds = retryDelaySeconds;
            }
        }

        @Override
        public void run() {
            try {
                retryTask.run();
            } catch (Throwable e) {
                int sleepSeconds = retryTime < retryDelaySeconds.length ? retryDelaySeconds[retryTime] : retryDelaySeconds[retryDelaySeconds.length - 1];
                if (retryTime < maxRetryTime) {
                    if (retryTime == 0){
                        TASK_QUEUE.add(this);
                        log.error("task execute error, " + sleepSeconds + " seconds do next... ", e);
                    }else {
                        log.error("retry " + retryTime + " times error, " + sleepSeconds + " seconds do next... ", e);
                    }
                    nextRetryMillis = System.currentTimeMillis() + sleepSeconds * 1000;
                }else {
                    log.error("retry " + retryTime + " times error", e);
                    log.error("retry snapshot: {}", JSON.toJSONStringWithDateFormat(retryTask.snapshot(), "yyyy-MM-dd HH:mm:ss"));
                    TASK_QUEUE.remove(this);
                    retryTask.retryFailed(e);
                }
                retryTime++;
            }
        }
    }

}
