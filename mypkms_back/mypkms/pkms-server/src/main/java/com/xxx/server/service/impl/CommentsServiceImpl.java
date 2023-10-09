package com.xxx.server.service.impl;

import com.xxx.server.UserUtils;
import com.xxx.server.pojo.Comments;
import com.xxx.server.mapper.CommentsMapper;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyong
 * @since 2021-04-12
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    /**
     * 根据ResourceID获取评论列表
     * @param resourceId
     * @return
     */
    @Override
    public List<Comments> getCommentsByResourceId(Long resourceId) {
        return commentsMapper.getCommentsByResourceId(resourceId, UserUtils.getCurrentUser().getUserID());
    }

    /**
     * 新增评论
     * @param comments
     * @return
     */
    @Override
    public RespBean addComment(Comments comments) {
        comments.setReviewerID(UserUtils.getCurrentUser().getUserID());
        commentsMapper.addComment(comments);
        if(1==comments.getResult()){
            return RespBean.success("新增评论成功！", comments);
        }
        return RespBean.error("新增评论失败！");
    }

    /**
     * 评论点赞
     * @param commentID
     */
    @Override
    public void likeComment(Long commentID) {
        commentsMapper.likeComment(commentID, UserUtils.getCurrentUser().getUserID());
    }
}
