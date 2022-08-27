package cn.root.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("加密后的密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("注册时间/登录时间")
    private String time;

    @ApiModelProperty("上次登录ip地址")
    private String ip;

    @ApiModelProperty("乐观锁version")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

}
