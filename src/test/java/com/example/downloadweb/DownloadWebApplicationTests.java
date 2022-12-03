package com.example.downloadweb;

import com.chen.dao.User;
import com.chen.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
@MapperScan("com.chen.mapper")
class DownloadWebApplicationTests {
    @Value("${file.upload.dir}")
    private String uploadFilePath;

    @Autowired
    private UserMapper userMapper;

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

    @Test
    void mysql(){
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }

}
