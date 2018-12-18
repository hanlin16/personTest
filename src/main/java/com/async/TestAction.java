package com.async;

/**
 * Created by Liuxd on 2018-09-11.
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Future;

public class TestAction {

    public static void main(String[] args) {
        System.out.println("主线程id：" + Thread.currentThread().getId() + "开始执行调用任务...");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        TaskService taskService = context.getBean(TaskService.class);
        for (int i = 0; i < 10; i++) {
            taskService.executeTask(i);

            Future<String> result = taskService.executeTaskHasReturn(i);

            /*try {
                System.out.println("异步程序执行结束，返回内容：" + result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/

        }

        context.close();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程id：" + Thread.currentThread().getId() + "程序结束!!");


    }
}