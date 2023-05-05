package com.lib.service;

import com.lib.dao.AdminMapper;
import com.lib.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin findAdminByUsername(String username) {
        return adminMapper.selectAdminByUsername(username);
    }

    public Admin findAdminById(int id) {
        return adminMapper.selectAdminById(id);
    }
}
