package com.lib.service;

import com.lib.dao.SysAdminMapper;
import com.lib.pojo.Admin;
import com.lib.pojo.AllUser;
import com.lib.pojo.SysAdmin;
import com.lib.pojo.User;
import com.lib.util.HostHolder;
import com.lib.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysAdminService {
    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private HostHolder hostHolder;

    public SysAdmin findSysAdminByUsername(String username) {
        return sysAdminMapper.selectSysByUsername(username);
    }

    public SysAdmin findSysAdminById(int id) {
        return sysAdminMapper.selectSysById(id);
    }

    public List<AllUser> searchUser(String username) {
        List<AllUser> allUsers = new ArrayList<>();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            allUsers.add(user);
        }
        Admin admin = adminService.findAdminByUsername(username);
        if (admin != null) {
            allUsers.add(admin);
        }
        return allUsers;
    }

    public int add(UserVo userVo) {
        if ("普通用户".equals(userVo.getType())) {
            if (userService.findUserByUsername(userVo.getUsername()) != null) {
                return -1;
            }
            userService.insertUser(new User(userVo.getUsername(), userVo.getPassword()));
        } else if ("管理员".equals(userVo.getType())) {
            if (adminService.findAdminByUsername(userVo.getUsername()) != null) {
                return -1;
            }
            adminService.insertAdmin(new Admin(userVo.getUsername(), userVo.getPassword()));
        }

        return 0;
    }

    public int delete(String type, int id) {
        if (!(hostHolder.getUsers() instanceof SysAdmin)) {
            return -1;
        }
        if ("普通用户".equals(type)) {
            userService.deleteUser(id);
        } else if ("管理员".equals(type)) {
            adminService.deleteAdmin(id);
        }
        return 0;
    }

}
