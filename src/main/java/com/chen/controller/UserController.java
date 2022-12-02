package com.chen.controller;

import com.chen.dao.User;
import com.chen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/selectAll")
    @ResponseBody
    public User select(){
        return userService.selectAll();
    }

    @GetMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user){
        System.out.println(user);
        userService.addUser(user);
        return "成功";
    }
}
