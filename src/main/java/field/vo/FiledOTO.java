package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = " 出参")
public class FiledOTO {

    @ApiModelProperty("订单")
    private OrderOTO order = new OrderOTO();

    @ApiModelProperty("险种")
    private OrderKindOTO orderKind = new OrderKindOTO();

    @ApiModelProperty("车辆")
    private OrderCarOTO car = new OrderCarOTO();

    @ApiModelProperty("投保人")
    private OrderPolicyOTO orderPolicy = new OrderPolicyOTO();

    @ApiModelProperty("受益人")
    private OrderBenefitOTO orderBenefit = new OrderBenefitOTO();


    @ApiModelProperty("客户车辆")
    private OrderCarOTO customerCar = new OrderCarOTO();

}