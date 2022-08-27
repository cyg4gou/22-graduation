package cn.root.bean.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容id")
    private String bodyId;

    @ApiModelProperty("点击数")
    @TableField(fill = FieldFill.INSERT)
    private Integer viewCount;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("活动过期时间")
    private Date expireTime;

    @ApiModelProperty("是否审核，默认0审核，1已审核")
    private int mark;

    @ApiModelProperty("审核未通过原因")
    private String remark;

    @ApiModelProperty("mysql乐观锁version")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty("是否删除，默认0，未删除，1已经删除")
    @TableField(fill = FieldFill.INSERT)
    private Boolean ifDelete;

}
