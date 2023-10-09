package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_categories")
@ApiModel(value="Categories对象", description="")
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    private String CategoryID;

    @ApiModelProperty(value = "类别名称")
    private String CayegoryName;

    @ApiModelProperty(value = "父类节点编码")
    private String ParentNodeID;

    @ApiModelProperty(value = "是否为父节点")
    private Integer IsParentFlag;

    @ApiModelProperty(value = "类别层级")
    private Integer Level;

    @ApiModelProperty(value = "祖先节点")
    private String Ancester;

    @ApiModelProperty(value = "子节点")
    @TableField(exist = false)
    private List<Categories> children;

    @ApiModelProperty(value = "角色列表")
    @TableField(exist = false)
    private List<Role> role;

    @ApiModelProperty(value = "返回结果，存储过程使用")
    @TableField(exist = false)
    private Integer result;

    @ApiModelProperty(value = "用户ID")
    @TableField(exist = false)
    private Integer userID;

    @ApiModelProperty(value = "text1")
    @TableField(exist = false)
    private String text1;

    @ApiModelProperty(value = "text2")
    @TableField(exist = false)
    private String text2;

    @ApiModelProperty(value = "text3")
    @TableField(exist = false)
    private String text3;

    @ApiModelProperty(value = "text4")
    @TableField(exist = false)
    private String text4;

    @ApiModelProperty(value = "zb")
    @TableField(exist = false)
    private Integer zb;


}
