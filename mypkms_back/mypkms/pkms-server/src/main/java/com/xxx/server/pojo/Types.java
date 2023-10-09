package com.xxx.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@TableName("t_types")
@ApiModel(value="Types对象", description="")
public class Types implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "TypeID", type = IdType.AUTO)
    @Excel(name = "类型编码")
    private Integer TypeID;

    @ApiModelProperty(value = "类型名称")
    @Excel(name = "类型名称")
    private String TypeName;

    @ApiModelProperty(value = "常见后缀名")
    @Excel(name = "常见后缀名")
    private String Suffix;

    @Excel(name = "常见后缀名", width = 200)
    private String note;


}
