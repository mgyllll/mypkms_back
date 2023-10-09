package com.xxx.server.controller;

import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.User;
import com.xxx.server.pojo.UserLoginParam;
import com.xxx.server.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import java.security.Principal;

/**
 * 登录
 *
 * @Author: Luo Yong
 * @Date: 2021-03-13 9:11
 * @Version 1.0
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return userService.login(userLoginParam.getUsername(), userLoginParam.getPassword(), userLoginParam.getCode(), request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/user/info")
    public User getUserInfo(Principal principal){
        if (null == principal){
            return null;
        }
        String username = principal.getName();
        User user = userService.getUserByUserName(username);
        user.setUserName(username);
        user.setPassword(null);
        user.setRoles(userService.getRoles(user.getUserID()));
        return user;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功！");
    }
}
