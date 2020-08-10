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
public class Factor {

    @ApiModelProperty("机构优先级")
    private Integer orgLevel;

    @ApiModelProperty("机构优先级")
    private Integer proLevel;

    @ApiModelProperty("时间段内优先级")
    private Integer timeLevel;

    @ApiModelProperty("时间段外优先级")
    private Integer timeOutLevel;

    @ApiModelProperty("开始时间")
    private Date begin;

    @ApiModelProperty("截止时间")
    private Date end;

    @ApiModelProperty("配置并发量")
    private Integer auth;

    @ApiModelProperty("最小并发量")
    private Integer minAuth;

    @ApiModelProperty("核心并发量")
    private Integer coreAuth;

    @ApiModelProperty("最大并发量")
    private Integer maxAuth;


}
