package javabasic.digui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "客户标签树")
public class LabelIdTreeITO {

    @ApiModelProperty("标签id")
    private Long id;

    @ApiModelProperty("子集id列表")
    private List<LabelIdTreeITO> subIds;

    public LabelIdTreeITO() {
    }

    public LabelIdTreeITO(Long id) {
        this.id = id;
    }

    public LabelIdTreeITO(Long id, List<LabelIdTreeITO> subIds) {
        this.id = id;
        this.subIds = subIds;
    }
}