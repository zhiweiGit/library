package com.lib.service;

import com.lib.dao.UserMapper;
import com.lib.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    public User findUserById(int id) {
        return userMapper.selectUserById(id);
    }
}
