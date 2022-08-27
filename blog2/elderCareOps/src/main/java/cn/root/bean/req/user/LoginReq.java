package cn.root.bean.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "登录Req")
public class LoginReq {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

}
