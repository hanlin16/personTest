package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = " 出参")
public class FiledOTO {

    @ApiModelProperty("客户订单")
    private OrderFiledOTO order = new OrderFiledOTO();

    @ApiModelProperty("客户车辆")
     private OrderCarOTO customerCar = new OrderCarOTO();
}