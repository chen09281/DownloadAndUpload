package com.chen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DownloadWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(DownloadWebApplication.class,args);
    }

}
