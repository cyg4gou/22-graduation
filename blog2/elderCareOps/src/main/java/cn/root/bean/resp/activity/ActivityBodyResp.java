package cn.root.bean.resp.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "查看活动体内容")
public class ActivityBodyResp {

    @ApiModelProperty("活动id")
    private String id;

    @ApiModelProperty("创建者id")
    private String author;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String body;

    @ApiModelProperty("点击数")
    private Integer viewCount;

    @ApiModelProperty("活动过期时间")
    private Date expireTime;

}
