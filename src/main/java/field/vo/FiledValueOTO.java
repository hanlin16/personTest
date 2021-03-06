package field.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = " 出参")
public class FiledValueOTO {

     @ApiModelProperty("属性名")
     private String name;

     @ApiModelProperty("属性值")
     private Object object;

}