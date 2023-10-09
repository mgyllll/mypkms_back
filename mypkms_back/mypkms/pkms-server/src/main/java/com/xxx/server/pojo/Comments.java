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
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_comments")
@ApiModel(value="Comments对象", description="")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CommentID", type = IdType.AUTO)
    private Long CommentID;

    private Integer ReviewerID;

    private String ReviewerName;

    private String Photo;

    private Long ResourceID;

    private Integer CommentLength;

    private String CommentContent;

    private LocalDate CreatedDate;

    private Long ParentCommentID;

    @ApiModelProperty(value = "返回结果，存储过程使用")
    @TableField(exist = false)
    private Integer result;

    @ApiModelProperty(value = "评论点赞人数")
    @TableField(exist = false)
    private Integer countLikes;

    @ApiModelProperty(value = "当前用户是否点赞")
    @TableField(exist = false)
    private Boolean liked;

}
