package com.chen.mapper;

import com.chen.dao.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    @Insert("insert into user (user_id,user_name,user_password,user_phone,user_email) values (null,#{user.userName},#{user.userPassword},#{user.userPhone},#{user.userEmail})")
    void addUser(@Param("user") User user);

    @Select("select * from user where user_id = #{id}")
    User queryById(@Param("id") int id);

    @Delete("delete from user where user_id=#{id}")
    void deleteById(int id);

    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where user_name = #{user.userName}")
    User queryByUserName(@Param("user") User user);

    @Update("update user set user_name=#{user.userName},user_password = #{user.userPassword},user_phone = #{user.userPhone},user_email = #{user.userEmail} where user_id = #{user.userId}")
    void editUser(@Param("user") User user);
}