package com.lib.service;

import com.lib.dao.UserMapper;
import com.lib.pojo.User;
import com.lib.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HostHolder hostHolder;

    public User findUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    public User findUserById(int id) {
        return userMapper.selectUserById(id);
    }

    public List<User> findAllUser() {
        return userMapper.selectAllUser();
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}
