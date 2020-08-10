package com.queue;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskInfo {

    @ApiModelProperty("任务总录音数")
    private Long totalNum;

    @ApiModelProperty("任务总录音时长")
    private Long totalTime;

    @ApiModelProperty("已完成录音数")
    private Long completedNum = 0l;

    @ApiModelProperty("已完成录音时长")
    private Long completedTime = 0l;

    @ApiModelProperty("处理效率")
    private Double rate;

}
