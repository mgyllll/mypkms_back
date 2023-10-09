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
@TableName("t_files")
@ApiModel(value="Files对象", description="")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "FileID", type = IdType.AUTO)
    private Long FileID;

    @ApiModelProperty(value = "文件名称")
    private String FileName;

    @ApiModelProperty(value = "资源编码")
    private Long ResourceID;

    @ApiModelProperty(value = "文件类型编号")
    private Integer TypeID;

    @ApiModelProperty(value = "文件大小")
    private Long FileSize;

    @ApiModelProperty(value = "文件路径")
    private String FilePath;

    @ApiModelProperty(value = "上传时间")
    private LocalDate UploadTime;

    @ApiModelProperty(value = "描述/资源内容简介")
    private String Description;

    @ApiModelProperty(value = "后缀名")
    @TableField(exist = false)
    private List<Types> types;

    @ApiModelProperty(value = "返回结果，存储过程使用")
    @TableField(exist = false)
    private Integer result;


}
