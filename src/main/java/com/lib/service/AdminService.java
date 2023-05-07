package com.lib.service;

import com.lib.dao.AdminMapper;
import com.lib.dao.BorrowMapper;
import com.lib.pojo.Admin;
import com.lib.pojo.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    public Admin findAdminByUsername(String username) {
        return adminMapper.selectAdminByUsername(username);
    }

    public Admin findAdminById(int id) {
        return adminMapper.selectAdminById(id);
    }

    public List<Borrow> findBorrows() {
        return borrowMapper.selectBorrowByStatus(0);
    }

    public List<Borrow> findBacks() {
        return borrowMapper.selectBorrowByStatus(3);
    }

    public List<Admin> findAllAdmin() {
        return adminMapper.selectAllAdmin();
    }

    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    public void insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
    }

    public void deleteAdmin(int id) {
        adminMapper.deleteAdmin(id);
    }
}
