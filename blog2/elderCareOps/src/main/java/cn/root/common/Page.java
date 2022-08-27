package cn.root.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "分页Base")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @ApiModelProperty(value = "当前页")
    protected Integer currentPage;

    @ApiModelProperty(value = "每一页数量")
    protected Integer pageSize;
}
