package com.xxx.server.controller;

import com.xxx.server.MyPKMSApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @Author: Luo Yong
 * @Date: 2021-03-13 14:19
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/users/basic/hello")
    public String hello2(){
        return "/users/basic/hello";
    }

    @GetMapping("/users/details/hello")
    public String hello3(){
        return "/users/details/hello";
    }
}
