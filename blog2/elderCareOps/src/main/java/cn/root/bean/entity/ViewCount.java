package cn.root.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("view_count")
public class ViewCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动id")
    private String activityId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("mysql乐观锁version")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty("是否删除，默认0，未删除，1已经删除")
    private Boolean ifDelete;


}
