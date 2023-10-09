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
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_areas")
@ApiModel(value="Areas对象", description="")
public class Areas implements Serializable {

    private static final long serialVersionUID = 1L;

    private String AreaID;

    private String AreaName;

    private String PYCode;

    private String Zip;

    private String PhoneCode;

    private String ParentnodeID;

    private Integer IsparentFlag;

    private String Ancester;

    private Integer level;

    @ApiModelProperty(value = "子节点")
    @TableField(exist = false)
    private List<Areas> children;

}
