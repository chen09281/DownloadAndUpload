package com.chen.controller;

import com.chen.dao.User;
import com.chen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 查询所有用户
     * @return All
     */
    @GetMapping("/selectAll")
    @ResponseBody
    public List<User> select(){
        return userService.selectAll();
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @GetMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user){
        if (userService.queryByUserName(user) != null){
            return "注册失败，用户名已存在";
        }
        userService.addUser(user);
        return "添加成功";
    }

    /**
     * 删除用户
     * @param id
     * @return boolean
     */
    @GetMapping("/delete")
    @ResponseBody
    public String deleteById(int id){
        while (true){
            if (userService.queryById(id) == null){
                return "用户不存在";
            } else{
                userService.deleteById(id);
            }
            break;
        }
        return "成功";
    }

    @GetMapping("/edit")
    @ResponseBody
    public String editUser(@RequestBody User user){
        userService.editUser(user);
        return "修改成功";
    }
}
