package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@ApiModel(description = "租户字段 出参")
public class TenantFieldOTO {

    @ApiModelProperty("字段编码")
    private String filedCode;

    @ApiModelProperty("字段名称")
    private String filedName;

    List<TenantFieldOTO> child = new ArrayList<>();

    public TenantFieldOTO() {
    }

    public TenantFieldOTO(String filedCode, String filedName) {
        this.filedCode = filedCode;
        this.filedName = filedName;
    }
}