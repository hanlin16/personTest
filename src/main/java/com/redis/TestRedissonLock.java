package com.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestRedissonLock {
    //最长等待时长
    private static final Integer WAIT_TIME = 20;
    //自动解锁时长
    private static final Integer TIME_OUT = 10;
    private static final String IP = "192.168.30.153";
    private static final String PORT = "6379";
    private static final RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress(IP + ":" + PORT);
        redissonClient = Redisson.create(config);
    }

    public static void main(String[] args) {
        //获取锁,场景:集群或分布式模式下可以使用前缀+订单号作为锁的key
        String lockKey = "cloudLock";
        RLock rLock = redissonClient.getLock(lockKey);
        //所有线程共用一把锁
        new TestRedissonLock().testExecuteThreads(rLock);

    }

    public void testExecuteThreads(RLock cloudLock) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            pool.execute(new IThread("【Thread_" + i+"】", cloudLock));
        }
    }


    class IThread implements Runnable {
        private String threadName;
        private RLock rLock;

        public IThread(String threadName, RLock rLock) {
            this.threadName = threadName;
            this.rLock = rLock;
        }

        @Override
        public void run() {
            boolean lockFlag = false;
            System.out.println(threadName + "开始执行");
            try {
                lockFlag = rLock.tryLock(WAIT_TIME, TIME_OUT, TimeUnit.SECONDS);
                if (lockFlag) {
                    System.out.println(this.threadName + "任务开始执行...");
                    //任务执行2秒钟
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(this.threadName + "任务完美结束!");
                } else {
                    System.out.println(threadName + "等待超时，执行失败！！！");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lockFlag) {
                    rLock.unlock();
                }
            }
        }

    }
}