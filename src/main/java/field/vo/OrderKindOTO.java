package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "商业险种 出参")
public class OrderKindOTO{

     @ApiModelProperty("主键id")
     private Long id;

     @ApiModelProperty("险种编码")
     private String kindCode;

     @ApiModelProperty("险种名称")
     private String kindName;

     @ApiModelProperty("保额")
     private String amount;

     @ApiModelProperty("保费")
     private String premium;

     @ApiModelProperty("玻璃单独破碎险：1是国产，2是进口修理期间费用补偿险：格式：” 天，金额”， 如：“1，50”表示1天50元钱")
     private String flag;

     @ApiModelProperty("订单id")
     private Integer orderId;

     @ApiModelProperty(value="创建时间", example = "2018-01-01 00:00:00")
     private Date createTime;

}