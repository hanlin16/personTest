package com.queue;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

public class DispatchTest {


    public static void main(String[] args) {
        Tenant tenant = Tenant.builder()
                .taskInfo(TaskInfo.builder().totalNum(3000L).totalTime(6000L).rate(5d).build())
                .factor(Factor.builder().orgLevel(1).proLevel(1).timeLevel(1).timeOutLevel(2)
                        .begin(DateUtils.addHours(new Date(), -6))
                        .end(DateUtils.addHours(new Date(), 6)).auth(10).minAuth(5).coreAuth(10).maxAuth(10).build())
                .queue(new LinkedBlockingQueue<>(10))
                .build();


    }


}
