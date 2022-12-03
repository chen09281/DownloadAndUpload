package com.chen.service;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Slf4j
public class MailService {
    // 定义邮件发送器
    @Autowired
    private JavaMailSender mailSender;

    // 定义邮件发送者
    @Value("${spring.mail.username}")
    private String form;

    public void sendEmail(String to,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(form);
        mailSender.send(message);
    }

    public void sendAttachmentsMail(String to, String title, String content, List<File> fileList){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(form);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content);
            String fileName = null;
            for (File file:fileList){
                fileName = MimeUtility.encodeText(file.getName(),"GB2312","B");
                helper.addAttachment(fileName,file);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
    }

}
