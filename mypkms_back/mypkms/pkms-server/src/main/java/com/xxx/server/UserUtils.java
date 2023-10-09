package com.xxx.server;

import com.xxx.server.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户工具类
 * @Author: Luo Yong
 * @Date: 2021-03-29 9:08
 * @Version 1.0
 */
public class UserUtils {


    /**
     * 获取当前登录用户
     * @return
     */
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
