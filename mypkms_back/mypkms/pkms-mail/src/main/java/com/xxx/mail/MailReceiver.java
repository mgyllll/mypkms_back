package com.xxx.mail;

import com.xxx.server.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @Author: Luo Yong
 * @Date: 2021-04-07 17:13
 * @Version 1.0
 */
@Component
public class MailReceiver {

    private static  final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;


    @RabbitListener(queues = "mail.welcome")
    public void  handler(User user){
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            // 发件人
            helper.setFrom(mailProperties.getUsername());
            // 收件人
            helper.setTo(user.getEmail());
            //主题
            helper.setSubject("欢迎邮件");
            // 发送日期
            helper.setSentDate(new Date());
            // 邮件内容
            Context context = new Context();
            context.setVariable("username", user.getUsername());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail);
            // 发送邮件
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            LOGGER.error("邮件发送失败========{}", e.getMessage());
        }
    }
}
