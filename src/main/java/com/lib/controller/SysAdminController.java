package com.lib.controller;

import com.lib.pojo.Admin;
import com.lib.pojo.AllUser;
import com.lib.pojo.User;
import com.lib.service.AdminService;
import com.lib.service.SysAdminService;
import com.lib.service.UserService;
import com.lib.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sysadmin")
public class SysAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SysAdminService sysAdminService;

    @GetMapping("")
    public String getIndex() {
        return "sysadmin/index";
    }

    @GetMapping("userList")
    public String userList(Model model) {
        List<UserVo> userVoList = new ArrayList<>();
        List<User> allUser = userService.findAllUser();
        for (User user : allUser) {
            UserVo userVo = new UserVo("普通用户", user.getId(), user.getUsername(), user.getPassword());
            userVoList.add(userVo);
        }
        List<Admin> allAdmin = adminService.findAllAdmin();
        for (Admin admin : allAdmin) {
            userVoList.add(new UserVo("管理员", admin.getId(), admin.getUsername(), admin.getPassword()));
        }
        model.addAttribute("list", userVoList);
        return "sysadmin/user";
    }

    @GetMapping("bookList")
    public String bookList() {
        return "sysadmin/book";
    }

    @GetMapping("sortList")
    public String sortList() {
        return "sysadmin/sort";
    }

    @GetMapping("addUser")
    public String addUser() {
        return "sysadmin/addUser";
    }

    @PostMapping("searchUser")
    public String searchUser(String username, Model model) {
        List<AllUser> allUsers = sysAdminService.searchUser(username);
        List<UserVo> userVoList = new ArrayList<>();
        for (AllUser allUser : allUsers) {
            if (allUser instanceof User) {
                userVoList.add(new UserVo("普通用户", allUser.getId(), allUser.getUsername(), allUser.getPassword()));
            } else if (allUser instanceof Admin) {
                userVoList.add(new UserVo("管理员", allUser.getId(), allUser.getUsername(), allUser.getPassword()));
            }
        }
        model.addAttribute("list", userVoList);
        return "sysadmin/user";
    }

    @PostMapping("add")
    public String addUser(UserVo userVo, Model model) {
        if (sysAdminService.add(userVo) == -1) {
            model.addAttribute("msg", "用户名已存在");
            model.addAttribute("username", userVo.getUsername());
            model.addAttribute("password", userVo.getPassword());
            return "sysadmin/addUser";
        }
        return "redirect:userList";
    }

    @GetMapping("update/{type}/{id}")
    public String updateUser(@PathVariable("type") String type, @PathVariable("id") int id, Model model) {
        UserVo userVo = null;
        if ("user".equals(type)) {
            User user = userService.findUserById(id);
            userVo = new UserVo("普通用户", id, user.getUsername(), user.getPassword());
        } else if ("amdin".equals(type)) {
            Admin admin = adminService.findAdminById(id);
            userVo = new UserVo("管理员", id, admin.getUsername(), admin.getPassword());
        }
        model.addAttribute("user", userVo);
        return "sysadmin/updateUser";
    }

    @PostMapping("update")
    public String update(String type, int id, String username, String password) {
        if ("普通用户".equals(type)) {
            User user = userService.findUserById(id);
            user.setUsername(username);
            user.setPassword(password);
            userService.updateUser(user);
        } else if ("管理员".equals(type)) {
            Admin admin = adminService.findAdminById(id);
            admin.setUsername(username);
            admin.setPassword(password);
            adminService.updateAdmin(admin);
        }
        return "redirect:userList";
    }

    @PostMapping("delete")
    @ResponseBody()
    public Map<String, Object> delete(String type, int id) {
        Map<String, Object> map = new HashMap<>();
        if (sysAdminService.delete(type, id) == -1) {
            map.put("code", -1);
        }
        map.put("code", 0);
        return map;
    }
}
