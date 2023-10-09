package com.xxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.Comments;
import com.xxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyong
 * @since 2021-04-12
 */
public interface ICommentsService extends IService<Comments> {

    /**
     * 根据ResourceID获取评论列表
     * @param resourceId
     * @return
     */
    List<Comments> getCommentsByResourceId(Long resourceId);

    /**
     * 新增评论
     * @param comments
     * @return
     */
    RespBean addComment(Comments comments);

    /**
     * 评论点赞
     * @param commentID
     */
    void likeComment(Long commentID);
}
