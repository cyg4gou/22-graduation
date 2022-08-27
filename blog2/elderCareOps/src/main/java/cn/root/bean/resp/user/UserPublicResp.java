package cn.root.bean.resp.user;

import cn.root.bean.resp.activity.ActivityResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 已弃用
 */
@Deprecated
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "公共信息Resp")
public class UserPublicResp {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("活动列表")
    private List<ActivityResp> activityRespArray;

}
