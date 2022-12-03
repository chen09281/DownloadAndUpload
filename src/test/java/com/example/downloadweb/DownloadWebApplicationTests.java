package com.example.downloadweb;

import com.chen.dao.User;
import com.chen.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;

@SpringBootTest
class DownloadWebApplicationTests {
    @Value("${file.upload.dir}")
    private String uploadFilePath;

//    @Autowired
//    private UserMapper userMapper;

//    @Autowired
//    MailService mailService;

    @Autowired
    JavaMailSender mailSender;

    @Test
    void url(){
        // 一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("陈你好啊~");
        mailMessage.setText("谢谢你的支持！");
        mailMessage.setTo("weifengchen198@gmail.com","1219853295@qq.com");
        mailMessage.setFrom("1219853295@qq.com");
        mailSender.send(mailMessage);
    }
    @Test
    void contextLoads() {
        String removeStr = "d:\\test\\";
        File file = new File(uploadFilePath);
        File[] fs = file.listFiles();
        for (File f:fs){
            if (!f.isDirectory())
                System.out.println(f.toString().replace(removeStr,""));
        }
    }

//    @Test
//    void mysql(){
//        List<User> users = userMapper.selectAll();
//        System.out.println(users);
//    }

//    @Test
//    void sendSimpleMailTest(){
//        mailService.sendEmail("1219853295@qq.com","第一封邮件","内容");
//    }
}
