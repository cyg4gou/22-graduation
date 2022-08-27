package cn.root.bean.resp.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户登录Resp")
public class UserLoginResp {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户token")
    private String token;

    @ApiModelProperty(value = "用户nickName")
    private String nickName;

    @ApiModelProperty(value = "登录ip地址变化")
    private String message;


}
