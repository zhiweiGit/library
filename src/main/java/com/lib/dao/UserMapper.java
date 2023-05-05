package com.lib.dao;

import com.lib.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUserByUsername(String username);

    User selectUserById(int id);

}
