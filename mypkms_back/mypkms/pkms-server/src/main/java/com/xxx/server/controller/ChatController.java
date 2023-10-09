package com.xxx.server.controller;

import com.xxx.server.pojo.User;
import com.xxx.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 在线聊天
 * @Author: Luo Yong
 * @Date: 2021-04-10 18:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/user")
    public List<User> getAllUsers(String keywords){
        return userService.getAllUsers(keywords);
    }

}
