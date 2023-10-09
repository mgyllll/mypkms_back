package com.xxx.server.controller;


import com.xxx.server.pojo.Comments;
import com.xxx.server.pojo.Files;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Types;
import com.xxx.server.service.ICommentsService;
import com.xxx.server.service.IFilesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoyong
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {


    @Autowired
    private ICommentsService commentsService;

    @ApiOperation(value = "根据ResourceID获取评论列表")
    @GetMapping("/")
    public List<Comments> getCommentsByResourceId(Long resourceId){
        return commentsService.getCommentsByResourceId(resourceId);
    }

    @ApiOperation(value = "新增评论")
    @PutMapping("/addComm")
    public RespBean addComment(@RequestBody Comments comments){
        return commentsService.addComment(comments);
    }

    @ApiOperation(value = "评论点赞")
    @GetMapping("/like")
    public void likeComment(Long commentID){
        commentsService.likeComment(commentID);
    }
}
