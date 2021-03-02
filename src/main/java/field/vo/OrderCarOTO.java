package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "车辆信息 出参")
public class OrderCarOTO{

     @ApiModelProperty("主键id")
     private Long id;

     @ApiModelProperty("客户id")
     private Long custId;

     @ApiModelProperty("省份")
     private String province;

     @ApiModelProperty("城市")
     private String city;

     @ApiModelProperty("区/县")
     private String county;

     @ApiModelProperty("车牌号")
     private String licenseNo;

     @ApiModelProperty("车架号")
     private String frameNo;

     @ApiModelProperty("品牌名称")
     private String modelName;

     @ApiModelProperty("品牌型号")
     private String modelCode;

     @ApiModelProperty("新车购置价")
     private String newCarPrice;

     @ApiModelProperty("发动机号")
     private String engineNo;

     @ApiModelProperty("座位数")
     private Integer seatCount;

     @ApiModelProperty(value="初登日期", example = "2018-01-01 00:00:00")
     private Date registerDate;

     @ApiModelProperty("是否过户 0：否，1：是")
     private Short specialCarFlag;

     @ApiModelProperty(value="过户时间", example = "2018-01-01 00:00:00")
     private Date specialCarDate;

     @ApiModelProperty(value="发证日期", example = "2018-01-01 00:00:00")
     private Date licenseDate;

     @ApiModelProperty("理赔信息")
     private String lipei;

     @ApiModelProperty(value="车辆保险止期", example = "2018-01-01 00:00:00")
     private Date lastEndDate;

     @ApiModelProperty("上年商业保费")
     private String lastBiPremium;

     @ApiModelProperty("上年较强保费")
     private String lastCiPremium;

     @ApiModelProperty("上年总保费")
     private String lastTotalPremium;

     @ApiModelProperty(value="创建时间", example = "2018-01-01 00:00:00")
     private Date createTime;

}