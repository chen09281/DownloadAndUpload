package com.chen.service;

import com.chen.dao.User;
import com.chen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public User queryById(int id) {
        return userMapper.queryById(id);
    }

    public void deleteById(int id) {
        userMapper.deleteById(id);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User queryByUserName(User user) {
        return userMapper.queryByUserName(user);
    }

    public void editUser(User user) {
        userMapper.editUser(user);
    }


}
