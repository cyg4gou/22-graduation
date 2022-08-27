package cn.root.bean.req.activity;


import cn.root.common.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "分页查询活动Req")
@AllArgsConstructor
@NoArgsConstructor
public class PageActivityReq extends Page {

    @ApiModelProperty(value = "作者id")
    private String authorId;



}
