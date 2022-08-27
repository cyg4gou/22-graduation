package cn.root.bean.req.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "发表文章")
public class PublishReq {

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String body;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty(value = "登陆人id")
    private String id;

    @ApiModelProperty("活动过期时间")
//    @JsonFormat(pattern = "yyyy-MM-dd \'T\' HH:mm:ss")
    private Date expireTime;

}
