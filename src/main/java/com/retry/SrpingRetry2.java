package com.retry;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-11-06 18:01
 */
public class SrpingRetry2 {


    public static void main(String[] args) throws Exception {
        RetryTemplate template = new RetryTemplate();

        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(2);

        template.setRetryPolicy(policy);

        String result = template.execute(
                new RetryCallback<String, Exception>() {
                    public String doWithRetry(RetryContext arg0) throws Exception {
                        System.out.println("失败...");
                        throw new NullPointerException("nullPointerException");
                    }
                }
                ,
                new RecoveryCallback<String>() {
                    public String recover(RetryContext context) throws Exception {
                        System.out.println("成功!!!");
                        return "recovery callback";
                    }
                }
        );
        System.out.println(result);
    }
}
