package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_resources")
@ApiModel(value="Resources对象", description="")
public class Resources implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ResourceID", type = IdType.AUTO)
    private Long ResourceID;

    @ApiModelProperty(value = "资源名称 ")
    private String Titile;

    @ApiModelProperty(value = "图片 ")
    private String photo;

    @ApiModelProperty(value = "阅读量")
    private Long ReadingQuantity;

    @ApiModelProperty(value = "类别编码")
    private String CategoryID;

    @ApiModelProperty(value = "用户编码")
    private Integer UserID;

    @ApiModelProperty(value = "标签")
    private String Tag;

    @ApiModelProperty(value = "创建时间")
    private LocalDate CreateDate;

    @ApiModelProperty(value = "最近一次修改时间")
    private LocalDate LastModifyDate;

    @ApiModelProperty(value = "描述/资源内容简介")
    private String Description;

    @ApiModelProperty(value = "是否共享")
    private String isShared;

    @ApiModelProperty(value = "0为未审核1为审核")
    private Integer Audited;

    @ApiModelProperty(value = "审核人")
    private Integer Auditor;

    @ApiModelProperty(value = "审核人姓名")
    private String AuditorName;

    @ApiModelProperty(value = "审核时间")
    private LocalDate AuditTime;

    @ApiModelProperty(value = "审核意见")
    private String AuditOpinion;

    @ApiModelProperty(value = "返回结果，存储过程使用")
    @TableField(exist = false)
    private Integer result;

    @ApiModelProperty(value = "用户ID")
    @TableField(exist = false)
    private Integer userID;

    @ApiModelProperty(value = "用户名")
    @TableField(exist = false)
    private String userName;

    @ApiModelProperty(value = "性别")
    @TableField(exist = false)
    private String gender;

    @ApiModelProperty(value = "头像")
    @TableField(exist = false)
    private String avatar;

    @ApiModelProperty(value = "资源点赞人数")
    @TableField(exist = false)
    private Integer countLikes;

    @ApiModelProperty(value = "当前用户是否点赞")
    @TableField(exist = false)
    private Boolean liked;

}
