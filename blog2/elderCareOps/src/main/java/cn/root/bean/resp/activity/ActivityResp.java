package cn.root.bean.resp.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "分页查询活动")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResp{

    @ApiModelProperty("活动id")
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容id")
    private String bodyId;

    @ApiModelProperty("点击数")
    private Integer viewCount;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建人id")
    private String createBy;

    @ApiModelProperty("创建人NickName")
    private String authorNickName;

    @ApiModelProperty("活动过期时间")
    private Date expireTime;

}
