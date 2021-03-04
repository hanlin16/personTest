package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ApiModel(description = "租户字段 出参")
public class TotalFieldOTO {

    @ApiModelProperty("字段编码")
    private String filedCode;

    @ApiModelProperty("字段名称")
    private String filedName;

    List<TotalFieldOTO> child = new ArrayList<>();

    public TotalFieldOTO() {
    }

    public TotalFieldOTO(String filedCode, String filedName) {
        this.filedCode = filedCode;
        this.filedName = filedName;
    }
}