package com.xxx.server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: Luo Yong
 * @Date: 2021-04-06 17:33
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        String pass = "";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("123456",hashPass);
        System.out.println(f);

    }
}
