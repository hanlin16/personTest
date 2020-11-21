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
 */
@Slf4j
public class RetryUtils {

    //默认次数
    private final static int DEFAULT_RETRY_TIMES = 6;
    //默认间隔
    private final static int[] DEFAULT_DELAY_SECONDS = {3, 30, 180, 600, 1800, 3600};

    private static Queue<RetryRunnable> TASK_QUEUE;
    private final Executor executor;

    public RetryUtils(ThreadPoolTaskExecutor executor, ThreadPoolTaskScheduler scheduler) {
        TASK_QUEUE = new ConcurrentLinkedQueue<>();
        this.executor = executor;

        //每秒检查一次：遍历任务队列，如需执行，线程池调度执行
        scheduler.scheduleAtFixedRate(() -> {
            for (RetryRunnable task : TASK_QUEUE) {
                long nextRetryMillis = task.nextRetryMillis;
                if (nextRetryMillis != -1 && nextRetryMillis <= System.currentTimeMillis()) {
                    task.nextRetryMillis = -1;
                    executor.execute(task);
                }
            }
        }, 1000);
    }

    public void doRetry(Task task) {
        doRetry(DEFAULT_RETRY_TIMES, DEFAULT_DELAY_SECONDS, task);
    }

    public void doRetry(int maxRetryTime, Task task) {
        doRetry(maxRetryTime, DEFAULT_DELAY_SECONDS, task);
    }

    public void doRetry(int[] retryDelaySeconds, Task task) {
        doRetry(retryDelaySeconds.length, retryDelaySeconds, task);
    }

    /**
     * @param maxRetryTime      最大重试次数
     * @param retryDelaySeconds 重试间隔时间数组
     * @param task              任务
     */
    public void doRetry(final int maxRetryTime, final int[] retryDelaySeconds, final Task task) {
        Runnable runnable = new RetryRunnable(maxRetryTime, retryDelaySeconds, task);
        executor.execute(runnable);
    }

    /**
     * 自定义线程类
     */
    private static class RetryRunnable implements Runnable {

        private final Task task;
        private final int maxRetryTimes;
        private final int[] retryDelaySeconds;

        private int retryTimes;
        private volatile long nextRetryMillis;

        //构造函数
        public RetryRunnable(final int maxRetryTimes, final int[] retryDelaySeconds, final Task task) {
            this.task = task;
            if (maxRetryTimes <= 0) {
                this.maxRetryTimes = DEFAULT_RETRY_TIMES;
            } else {
                this.maxRetryTimes = maxRetryTimes;
            }
            if (retryDelaySeconds == null || retryDelaySeconds.length == 0) {
                this.retryDelaySeconds = DEFAULT_DELAY_SECONDS;
            } else {
                this.retryDelaySeconds = retryDelaySeconds;
            }
        }

        //业务执行方法
        @Override
        public void run() {
            try {
                task.run();
            } catch (Throwable e) {
                int sleepSeconds = retryTimes < retryDelaySeconds.length ? retryDelaySeconds[retryTimes] : retryDelaySeconds[retryDelaySeconds.length - 1];
                if (retryTimes < maxRetryTimes) {
                    if (retryTimes == 0) {
                        TASK_QUEUE.add(this);
                        log.error("task executed error, " + sleepSeconds + " seconds do next... ", e);
                    } else {
                        log.error("retry " + retryTimes + " times error, " + sleepSeconds + " seconds do next... ", e);
                    }
                    nextRetryMillis = System.currentTimeMillis() + sleepSeconds * 1000;
                } else {
                    log.error("retry " + retryTimes + " times error", e);
                    log.error("retry snapshot: {}", JSON.toJSONStringWithDateFormat(task.snapshot(), "yyyy-MM-dd HH:mm:ss"));
                    TASK_QUEUE.remove(this);
                    task.retryFailed(e);
                }
                retryTimes++;
            }
        }
    }

}
