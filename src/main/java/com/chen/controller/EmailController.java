package com.chen.controller;

import com.chen.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    MailService mailService;

    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param content
     * @return String
     */
    @GetMapping("send")
    @ResponseBody
    public String send(String to, String subject, String content){
        mailService.sendEmail(to,subject,content);
        return "发送成功";
    }

    @GetMapping("/getCheckCode")
    @ResponseBody
    public String checkCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(8999) + 1000);
        String message = "你的验证码为:" + checkCode;
        try {
            mailService.sendEmail(email,"注册验证码",message);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(checkCode);
        return "发送成功";
    }
}
