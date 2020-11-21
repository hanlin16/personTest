package com.iretry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class IRetryTest {

    public static void main(String[] args) {
        RetryUtils retryUtils = new RetryUtils();

        IRetryTest iRetryTest = new IRetryTest();
        iRetryTest.executeBusiness(retryUtils, new IRetryTest().new UserInfo("Jack"));

    }

    //需要重试的业务方法
    private void executeBusiness(RetryUtils retryUtils, UserInfo user) {

        retryUtils.doRetry(new int[]{6, 12}, new Task() {
            @Override
            public void run() throws Exception {
                user.setName("Henry");
                log.info("执行业务,给员工修改名称为：" + user.getName());
                Integer a = 1 / 0;

            }

            //最终失败后，支持回滚业务
            @Override
            public void retryFailed(Throwable e) {
                user.setName("Jack");
                log.info("重试失败，业务数据回滚...");
                log.info("用户名称为：" + user.getName());
            }

            @Override
            public Object snapshot() {
                return user;
            }
        });
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class UserInfo {
        private String name;
    }


}
