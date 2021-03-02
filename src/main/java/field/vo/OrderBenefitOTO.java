package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "受益人 出参")
public class OrderBenefitOTO{

     @ApiModelProperty("主键id")
     private Long id;

     @ApiModelProperty("保单id")
     private Long orderId;

     @ApiModelProperty("姓名")
     private String name;

     @ApiModelProperty("手机")
     private String mobile;

     @ApiModelProperty("邮件")
     private String email;

     @ApiModelProperty("证件类型")
     private Short idCardType;

     @ApiModelProperty("证件号码")
     private String idCard;

     @ApiModelProperty("证件类型是否长期有效 0:否,1:是")
     private Short longTerm;

     @ApiModelProperty(value="证件有效期 YYYY-MM-DD,身份证时必填", example = "2018-01-01 00:00:00")
     private Date validDate;

     @ApiModelProperty("出生日期")
     private String birthday;

     @ApiModelProperty("性别")
     private Short gender;

     @ApiModelProperty("受益人与被投保人关系")
     private Short relation;

     @ApiModelProperty("受益顺序")
     private Integer benefitOrder;

     @ApiModelProperty("受益人类型 生存受益人：0 身故受益人：1")
     private Short benefitType;

     @ApiModelProperty("受益比例 受益份额 必填，小于等于1的数，比如0.9等")
     private Double benefitRate;

     @ApiModelProperty("常驻省")
     private String province;

     @ApiModelProperty("常驻市")
     private String city;

     @ApiModelProperty("常驻区/县")
     private String county;

     @ApiModelProperty("家庭住址")
     private String address;

     @ApiModelProperty("邮编")
     private String zipCode;

     @ApiModelProperty("身高 单位CM")
     private Integer height;

     @ApiModelProperty("体重 单位KG")
     private Integer weight;

     @ApiModelProperty("学历")
     private Short degree;

     @ApiModelProperty("年收入 单位元")
     private Integer annualIncome;

     @ApiModelProperty("社保标记 0：无社保；1：有社保")
     private String socialSecurity;

     @ApiModelProperty(value="创建时间", example = "2018-01-01 00:00:00")
     private Date createTime;

}