package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = " 出参")
public class OrderOTO{

     @ApiModelProperty("主键id")
     private Long id;

     @ApiModelProperty("客户id")
     private Long custId;

     @ApiModelProperty("车辆id")
     private Long carId;

     @ApiModelProperty("保险产品名称")
     private String productName;

     @ApiModelProperty("保险产品类型")
     private Short productType;

     @ApiModelProperty("投保份数")
     private Integer num;

     @ApiModelProperty("保额")
     private String amnout;

     @ApiModelProperty("保费")
     private String premium;

     @ApiModelProperty("保单状态")
     private Short orderStatus;

     @ApiModelProperty("续保状态")
     private Short renewalStatus;

     @ApiModelProperty("保单号")
     private String orderNum;

     @ApiModelProperty("投保单号")
     private String policyNum;

     @ApiModelProperty("保险期限:D-天,W-周,M-月,Y-年,A-至N岁,终身;示例:D3,W5,M10,Y10,A60,ZS")
     private String insureTerm;

     @ApiModelProperty("缴费期限:Y-年,A-至N岁,趸交;分别示例:Y10,A60,DJ")
     private String chargeTerm;

     @ApiModelProperty("续费 0:不自动续费，1:自动续费")
     private Short autoPay;

     @ApiModelProperty("开户行编码")
     private String bankCode;

     @ApiModelProperty("开户行名称")
     private String bankName;

     @ApiModelProperty("银行账号")
     private String bankAccount;

     @ApiModelProperty(value="保单起始时间", example = "2018-01-01 00:00:00")
     private Date startDate;

     @ApiModelProperty(value="保单终止时间", example = "2018-01-01 00:00:00")
     private Date endDate;

     @ApiModelProperty(value="出单时间", example = "2018-01-01 00:00:00")
     private Date insuredTime;

     @ApiModelProperty(value="支付时间", example = "2018-01-01 00:00:00")
     private Date payTime;

     @ApiModelProperty("投保省份")
     private String province;

     @ApiModelProperty("投保城市")
     private String city;

     @ApiModelProperty("投保区县")
     private String county;

     @ApiModelProperty(value="创建时间", example = "2018-01-01 00:00:00")
     private Date createTime;

}