package com.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.BlockingQueue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant {

    private BlockingQueue<String> queue ;

    private TaskInfo taskInfo;

    private Factor factor;



}
