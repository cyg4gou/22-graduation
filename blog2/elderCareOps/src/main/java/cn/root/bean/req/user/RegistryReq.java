package cn.root.bean.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "注册用户Req")
public class RegistryReq {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickName;

}
