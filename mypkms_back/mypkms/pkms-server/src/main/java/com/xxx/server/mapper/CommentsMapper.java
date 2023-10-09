package com.xxx.server.mapper;

import com.xxx.server.pojo.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyong
 * @since 2021-04-12
 */
public interface CommentsMapper extends BaseMapper<Comments> {

    /**
     * 根据ResourceID获取评论列表
     * @param resourceId
     * @return
     */
    List<Comments> getCommentsByResourceId(Long resourceId, Integer userId);

    /**
     * 新增评论
     * @param comments
     */
    void addComment(Comments comments);

    /**
     * 评论点赞
     * @param commentID
     * @param userID
     */
    void likeComment(Long commentID, Integer userID);
}
