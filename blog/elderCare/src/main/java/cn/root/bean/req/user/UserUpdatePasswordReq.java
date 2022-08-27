package cn.root.bean.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "修改密码Req")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePasswordReq {

    /*@ApiModelProperty("user的主键id")
    private String id;*/

    @ApiModelProperty("之前的密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String newPassword;

}
