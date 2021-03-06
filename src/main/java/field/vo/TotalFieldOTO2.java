package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "租户字段 出参")
public class TotalFieldOTO2 {

    @ApiModelProperty("模块编码")
    private String moduleCode;

    @ApiModelProperty("字段编码")
    private String filedCode;


    public TotalFieldOTO2() {
    }

    public TotalFieldOTO2(String filedCode, String moduleCode) {
        this.filedCode = filedCode;
        this.moduleCode = moduleCode;
    }
}