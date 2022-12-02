package com.chen.service;

import com.chen.dao.User;
import com.chen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User selectAll() {
        return userMapper.selectAll();
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
