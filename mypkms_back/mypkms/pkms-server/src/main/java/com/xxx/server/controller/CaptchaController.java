package com.xxx.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

/**
 * 验证码
 *
 * @Author: Luo Yong
 * @Date: 2021-03-15 14:33
 * @Version 1.0
 */
@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha", produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        //定义response输出类型为image/jpeg类型
        response.setDateHeader("Expire", 0);
        //Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        //Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        //Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        //return a jpeg.
        response.setContentType("image/jpeg");

        /*-------------------------------生成验证码 begin------------------------------------*/
        //验证码内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容："+text);
        //
        request.getSession().setAttribute("captcha", text);
        //
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*-------------------------------生成验证码 end------------------------------------*/

    }
}
