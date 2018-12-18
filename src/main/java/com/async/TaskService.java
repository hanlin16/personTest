package com.async;

/**
 * Created by Liuxd on 2018-09-11.
 */

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TaskService {

    //声明异步
    @Async
    public void executeTask(Integer i) {
        System.out.println("线程ID：" + Thread.currentThread().getId() + "执行异步任务:" + i);
    }

    //声明异步
    @Async
    public Future<String> executeTaskHasReturn(Integer i) {
        System.out.println("线程ID：" + Thread.currentThread().getId() + "执行异步有返回的任务:" + i);

        return new AsyncResult<>("success:"+i);
    }


}