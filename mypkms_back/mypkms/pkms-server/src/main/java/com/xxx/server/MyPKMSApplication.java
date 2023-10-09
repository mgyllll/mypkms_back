package com.xxx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * 启动类
 *
 * @Author: Luo Yong
 * @Date: 2021-03-12 16:10
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.xxx.server.mapper")
public class MyPKMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPKMSApplication.class, args);
    }
}
