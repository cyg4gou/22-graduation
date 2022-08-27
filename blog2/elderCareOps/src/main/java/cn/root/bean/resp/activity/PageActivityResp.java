package cn.root.bean.resp.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "活动的分页查询Resp")
public class PageActivityResp {

    @ApiModelProperty(value = "总记录数")
    private Integer counts;

    @ApiModelProperty(value = "当前第几页")
    private Integer currentPage;

    @ApiModelProperty(value = "活动List<ActivityResp>")
    private List<ActivityResp> list;

}
