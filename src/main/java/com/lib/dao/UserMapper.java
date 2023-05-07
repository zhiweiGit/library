package com.lib.dao;

import com.lib.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectUserByUsername(String username);

    User selectUserById(int id);

    List<User> selectAllUser();

    int updateUser(User user);

    int insertUser(User user);

    int deleteUser(int id);

}
