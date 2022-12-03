package com.chen.mapper;

import com.chen.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    
    @Select("select * from user")
    User selectAll();

    @Insert("insert into user (user_id,user_name,user_password,user_phone,user_email) values (null,#{user.userName},#{user.userPassword},#{user.userPhone},#{user.userEmail})")
    void addUser(@Param("user") User user);
}